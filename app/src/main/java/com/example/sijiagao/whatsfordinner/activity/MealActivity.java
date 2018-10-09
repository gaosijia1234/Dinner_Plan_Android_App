package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;

import java.util.List;
import java.util.TreeMap;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        /*
        Backend test
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        TreeMap<String, Integer> test = db.getAllMeal();
        List<String> two = db.getMealPlanByDay("Monday");

        db.assignRecipeToPlanSlot("Monday", "Breakfast", "Lemon");
        db.assignRecipeToPlanSlot("Monday", "Lunch", "Lemon");
        db.assignRecipeToPlanSlot("Monday", "Dinner", "Lemon");
        List<String> one = db.getMealPlanByDay("Monday");
        TreeMap<String, Integer> test2 = db.getAllMeal();

        db.assignRecipeToPlanSlot("Monday", "Breakfast", "Eating Out");
        db.assignRecipeToPlanSlot("Monday", "Lunch", "Eating Out");
        db.assignRecipeToPlanSlot("Monday", "Dinner", "Eating Out");
        List<String> three = db.getMealPlanByDay("Monday");
        TreeMap<String, Integer> test3 = db.getAllMeal();*/
    }


    protected void mondayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Monday");
        startActivity(i);
    }

    protected void tuesdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Tuesday");
        startActivity(i);
    }

    protected void wednesdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Wednesday");
        startActivity(i);
    }

    protected void thursdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Thursday");
        startActivity(i);
    }

    protected void fridayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Friday");
        startActivity(i);
    }

    protected void saturdayBtnClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Saturday");
        startActivity(i);
    }

    protected void btnSundayClick(View view) {
        Intent i = new Intent(this, DayMealActivity.class);
        i.putExtra("day", "Sunday");
        startActivity(i);
    }

}
