package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;

import java.util.ArrayList;
import java.util.List;

public class DayMealActivity extends AppCompatActivity  {

    TextView breakfastInputTV;
    TextView lunchInputTV;
    TextView dinnerInputTV;
    public static final String EATINGOUT = "eatingout";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);

        Spinner breakfast_sp = (Spinner) findViewById(R.id.breakfast_sp);

        // Spinner click listener
        breakfast_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); {

        }

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        breakfast_sp.setAdapter(dataAdapter);

    }



    public void breakfastDoneBtnClick(View view){
        breakfastInputTV = findViewById(R.id.breakfastInputTV);
        String bfInput = breakfastInputTV.getText().toString();

        Spinner breakfast_sp = (Spinner) findViewById(R.id.breakfast_sp);

        breakfastInputTV.setText(breakfast_sp.getPrompt());

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
