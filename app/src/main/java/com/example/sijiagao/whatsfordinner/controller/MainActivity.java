package com.example.sijiagao.whatsfordinner.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.sijiagao.whatsfordinner.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton newDishImageBtn = findViewById(R.id.newDishImageBtn);
        ImageButton recipeImageBtn = findViewById(R.id.recipeImageBtn);
        ImageButton mealImageBtn = findViewById(R.id.mealImageBtn);
        ImageButton groceryImageBtn = findViewById(R.id.groceryImageBtn);

        // new Onlick
        newDishImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newDishIntent = new Intent(getApplicationContext(), NewdishActivity.class);
                startActivity(newDishIntent);
            }
        });

        recipeImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });

        mealImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mealsIntent = new Intent(getApplicationContext(), MealActivity.class);
                startActivity(mealsIntent);
            }
        });

        groceryImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent groceryIntent = new Intent(getApplicationContext(), GroceryActivity.class);
                startActivity(groceryIntent);
            }
        });

    }
}
