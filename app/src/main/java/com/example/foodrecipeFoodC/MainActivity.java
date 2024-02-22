package com.example.foodrecipeFoodC;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.foodrecipeFoodC.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Meal> meals;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ActivityMainBinding binding;
    private ViewModel viewModel;
    ImageView imageView3, user_logo, sample_logo,close_btn;
    SearchView search_bar;
    SearchView mainSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        sample_logo = findViewById(R.id.sample_logo);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //close_btn = findViewById(R.id.close_btn);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ViewModel.class);

        // Initialize meals list
        meals = new ArrayList<>();

        // Set up RecyclerView
        recyclerView = binding.recyleView;
        mealAdapter = new MealAdapter(meals, this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mealAdapter);


        // Observe changes in meals data
        getMeals();

    }

    private void getMeals() {
        viewModel.getAllMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> mealsFromList) {
                if (mealsFromList != null) {
                    meals.clear();  // Clear existing data
                    meals.addAll(mealsFromList);
                    displayMeals();
                } else {
                    Log.e("MainActivity", "Received null meals list");
                }
            }
        });
    }

    private void displayMeals() {
        mealAdapter.notifyDataSetChanged();
        Log.i("sample", "" + meals.get(0).getStrMeal());
    }


    public void custom(View view) {
        ImageView i1 = findViewById(R.id.user_logo);
        i1.setAlpha(0);
        ImageView i2 = findViewById(R.id.imageView3);
        i2.setAlpha(0);
        ImageView i3 = findViewById(R.id.sample_logo);
        i3.setAlpha(0);
        SearchView s1 = findViewById(R.id.searchbar);
        s1.setAlpha(1);
        s1.clearFocus();
        s1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        List<Meal> filteredList = new ArrayList<>();
        for (Meal meal : meals) {
            if (meal.getStrMeal().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(meal);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();

        } else {
            mealAdapter.setMealsList(filteredList);
        }

    }

    public void recipeDescription(View v) {
        Object tag = v.getTag();


        if (tag != null) {
            // Convert the tag to the appropriate type if needed
            int tagValue = Integer.parseInt(tag.toString());

            // Now you can use the tagValue as needed
            // For example, you can use it in an Intent to pass it to another activity
            Intent intent = new Intent(this, RecipeDescription.class);

            // Assuming meals is an ArrayList<Meal> or some collection
            if (tagValue >= 0 && tagValue < meals.size()) {
                Meal selectedMeal = meals.get(tagValue);
                intent.putExtra("selectedMeal",(Parcelable) selectedMeal);
                startActivity(intent);
            } else {
                // Handle the case when tagValue is out of bounds
                Log.e("recipeDescription", "Invalid tagValue: " + tagValue);
            }
        }
    }

    public void sample(View v) {
        Log.i("test","sample");
    }


}