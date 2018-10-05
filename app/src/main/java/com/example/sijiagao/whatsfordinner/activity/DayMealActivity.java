package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sijiagao.whatsfordinner.R;

public class DayMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);
    }

    // didn't work
    public void dayMealDoneBtnClick(View view){

        // last line: go back to MealActivity
        Intent i = new Intent(this, MealActivity.class);
        startActivity(i);
    }

}
