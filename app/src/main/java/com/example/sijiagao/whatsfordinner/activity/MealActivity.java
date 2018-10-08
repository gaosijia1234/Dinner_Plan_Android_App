package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sijiagao.whatsfordinner.R;

public class MealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
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
