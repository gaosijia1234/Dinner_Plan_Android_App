package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;

public class DayMealActivity extends AppCompatActivity {

    TextView breakfastInputTV;
    TextView lunchInputTV;
    TextView dinnerInputTV;
    public static final String EATINGOUT = "eatingout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);
    }

    public void breakfastDoneBtnClick(View view){
        breakfastInputTV = findViewById(R.id.breakfastInputTV);
        String bfInput = breakfastInputTV.getText().toString();

        // if eating out
        if (bfInput.equals(EATINGOUT)){
            // doesn't do anything to the recipeTableList
        }

        // if to it is the first time to input the meal choice
        firstTimeMealChoice(bfInput);

        // if want to update the input
        updateMealChoice(bfInput);


    }

    public void lunchDoneBtnClick(View view){
        lunchInputTV = findViewById(R.id.lunchInputTV);
        String bfInput = lunchInputTV.getText().toString();

        // logic same as breakfast
    }

    public void dinnerDoneBtnClick(View view){
        dinnerInputTV = findViewById(R.id.dinnerInputTV);
        String bfInput = dinnerInputTV.getText().toString();

        // logic same as breakfast

    }

    public String updateMealChoice(String input){


        return "updateMealChoice method is called";
    }

    public String firstTimeMealChoice(String input){


        return "firstTimeMealChoice method is called";
    }

    public void dayMealDoneBtnClick(View view){

        // last line: go back to MealActivity
        Intent i = new Intent(this, MealActivity.class);
        startActivity(i);
    }


}
