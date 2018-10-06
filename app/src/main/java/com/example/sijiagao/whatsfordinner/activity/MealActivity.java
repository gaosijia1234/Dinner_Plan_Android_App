package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;

import java.util.TreeMap;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        //DatabaseHelper db = DatabaseHelper.getInstance(this);
        //db.addRecipeToMeal("Cherry Water");
        //db.consumeRecipeFromMeal("Cherry Water");
        //db.consumeRecipeFromMeal("Cherry Water");
        //TreeMap<String, Integer> test = db.getAllMeal();
    }


    protected void mondayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 1);
        startActivity(i);
    }

    protected void tuesdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 2);
        startActivity(i);
    }

    protected void wednesdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 3);
        startActivity(i);
    }

    protected void thursdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 4);
        startActivity(i);
    }

    protected void fridayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 5);
        startActivity(i);
    }

    protected void saturdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 6);
        startActivity(i);
    }

    protected void btnSundayClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", 7);
        startActivity(i);
    }

}
