package com.example.foodrecipeFoodC;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecipeDescription extends AppCompatActivity {
    ImageView recipeImg,mealCategory;
    TextView recipeTittle,recipeInstrucions;
    LinearLayout ingredientsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_description);
        Meal receivedMeal = getIntent().getParcelableExtra("selectedMeal");
        recipeImg = findViewById(R.id.recipeimg);
        recipeImg.setImageResource(R.drawable.i3);
        mealCategory = findViewById(R.id.mealCategory);
        ingredientsLayout = findViewById(R.id.ingredient_layout);
        recipeInstrucions = findViewById(R.id.mealInstructions);
        if (receivedMeal != null) {
            String mealThumb = receivedMeal.getStrMealThumb();
            String mealName = receivedMeal.getStrMeal();
            ArrayList<String> ingredients = new ArrayList<>();
            String ingredient2 = receivedMeal.getStrIngredient2();
            String instructions= receivedMeal.getStrInstructions();
            Glide.with(this).load(mealThumb).into(recipeImg);
            for (int i = 1; i <= 20; i++) {
                String ingredient = "strIngredient" + i;
                String value = "";

                switch (i) {
                    case 1: value = receivedMeal.getStrIngredient1(); break;
                    case 2: value = receivedMeal.getStrIngredient2(); break;
                    case 3: value = receivedMeal.getStrIngredient3(); break;
                    case 4: value = receivedMeal.getStrIngredient4(); break;
                    case 5: value = receivedMeal.getStrIngredient5(); break;
                    case 6: value = receivedMeal.getStrIngredient6(); break;
                    case 7: value = receivedMeal.getStrIngredient7(); break;
                    case 8: value = receivedMeal.getStrIngredient8(); break;
                    case 9: value = receivedMeal.getStrIngredient9(); break;
                    case 10: value = receivedMeal.getStrIngredient10(); break;
                    case 11: value = receivedMeal.getStrIngredient11(); break;
                    case 12: value = receivedMeal.getStrIngredient12(); break;
                    case 13: value = receivedMeal.getStrIngredient13(); break;
                    case 14: value = receivedMeal.getStrIngredient14(); break;
                    case 15: value = receivedMeal.getStrIngredient15(); break;
                    case 16: value = receivedMeal.getStrIngredient16(); break;
                    case 17: value = receivedMeal.getStrIngredient17(); break;
                    case 18: value = receivedMeal.getStrIngredient18(); break;
                    case 19: value = receivedMeal.getStrIngredient19(); break;
                    case 20: value = receivedMeal.getStrIngredient20(); break;
                }

                if (value != null && !value.equals("")) {
                    ingredients.add(value);
                }
            }
            recipeTittle = findViewById(R.id.recipe_title);
            recipeTittle.setText(mealName);
            if (receivedMeal.getStrMeal() == "Vegetarian" || receivedMeal.getStrMeal() == "Starter" || receivedMeal.getStrMeal() == "Miscellaneous" || receivedMeal.getStrMeal() == "Dessert") {
                mealCategory.setImageResource(R.drawable.vegicon);
            }
            else {
                mealCategory.setImageResource(R.drawable.nonvegicon);
            }
            for (String ingredient : ingredients) {
                LinearLayout l1 = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                l1.setLayoutParams(layoutParams2);
                TextView textDecoration= new TextView(this);
                textDecoration.setText("I");
                textDecoration.setTextColor(ContextCompat.getColor(this, R.color.blue));
                textDecoration.setTextSize(25);

                TextView textView = new TextView(this);
                textView.setText(ingredient);
                // You can customize the layout parameters as needed
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );


                textDecoration.setLayoutParams(layoutParams);
                l1.addView(textDecoration);

                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutParams1.setMargins(20, 0, 0, 8);
                textView.setLayoutParams(layoutParams1);
                textView.setTextColor(ContextCompat.getColor(this, R.color.variant_black));
                l1.addView(textView);
                ingredientsLayout.addView(l1);
            }
            recipeInstrucions.setText(instructions);
        }
    }
}