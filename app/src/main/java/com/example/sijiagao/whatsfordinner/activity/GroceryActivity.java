package com.example.sijiagao.whatsfordinner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;

import java.util.TreeMap;

public class GroceryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        DatabaseHelper db = DatabaseHelper.getInstance(this);
        db.addRecipeToMeal("Lemon Water");
        db.addRecipeToMeal("Lemon Chicken");
        TreeMap<String, Integer> test = db.getAllMeal();
        TreeMap<String, IngredientUnit> test2 = db.getAllGroceryItems();
        db.clearGrocery();
        TreeMap<String, Integer> test3 = db.getAllMeal();
        TreeMap<String, IngredientUnit> test4 = db.getAllGroceryItems();

    }
}
