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

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DayMealActivity extends AppCompatActivity {
    private Spinner breakfast_sp;
    private Spinner lunch_sp;
    private Spinner dinner_sp;
    List<String> igList ;
    private String today;


    public static final String EATINGOUT = "eatingout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);

        setUp();
        linsterSetUp();


    }

    public void setUp(){
        DatabaseHelper db = DatabaseHelper.getInstance(this);

        Log.i("ggggg","yy");
        TextView day = findViewById(R.id.daymealLabelTV);
        today = getIntent().getStringExtra("day");
        day.setText(today);

//        Log.i("ggggg",db.getMealPlanByDay(today).get(0));
        igList = new ArrayList<String>();

        igList.add("Automobile");
        igList.add("Business Services");
        igList.add("Computers");
        igList.add("Education");
        igList.add("Personal");
        igList.add("Travel");
       // Log.i("ggggg",db.getMealPlanByDay(today).get(0));


//        igList.add(db.getMealPlanByDay(today).get(0));

        breakfast_sp = (Spinner) findViewById(R.id.breakfast_sp);
        lunch_sp     = (Spinner) findViewById(R.id.lunch_sp);
        dinner_sp    = (Spinner) findViewById(R.id.dinner_sp);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, igList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfast_sp.setAdapter(dataAdapter);
        lunch_sp.setAdapter(dataAdapter);
        dinner_sp.setAdapter(dataAdapter);

    }


    public void linsterSetUp() {
        breakfast_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lunch_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dinner_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void breakfastDoneBtnClick(View view){

    }

    public void lunchDoneBtnClick(View view){

        // logic same as breakfast
    }

    public void dinnerDoneBtnClick(View view){

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
