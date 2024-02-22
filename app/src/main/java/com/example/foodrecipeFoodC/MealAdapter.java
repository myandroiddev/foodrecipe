package com.example.foodrecipeFoodC;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeFoodC.databinding.RecipeItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private ArrayList<Meal> meals = new ArrayList<>();
    private Context context;

    public MealAdapter(ArrayList<Meal> meals, Context context) {
        this.meals = meals;
        this.context = context;
    }

    public void setMealsList(List<Meal> filteredList) {
        this.meals = (ArrayList<Meal>) filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecipeItemBinding recipeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recipe_item,parent,false);
        return new MyViewHolder(recipeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal newMeal = meals.get(position);
        holder.recipeItemBinding.setMeal(newMeal);
        holder.recipeItemBinding.recipeCard.setTag(position);
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RecipeItemBinding recipeItemBinding;

        public MyViewHolder(@NonNull RecipeItemBinding recipeItemBinding) {
            super(recipeItemBinding.getRoot());
            this.recipeItemBinding = recipeItemBinding;
        }
    }

}
