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
    private int breakfast_position;
    private int lunch_position;
    private int dinner_position;
    List<String> igList = new ArrayList<String>();
    private String today;
    DatabaseHelper db = DatabaseHelper.getInstance(this);
    private ArrayAdapter<String> dataAdapter;


    public static final String EATINGOUT = "eatingout";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_meal);
        setUp();
        linsterSetUp();


    }

    public void setUp(){
        Log.i("ggggg","yy");
        TextView day = findViewById(R.id.daymealLabelTV);
        today = getIntent().getStringExtra("day");
        day.setText(today);

        TreeMap<String,Integer> mealList=  db.getAllMeal();


        if (!db.getAllMeal().isEmpty())
        { igList = new ArrayList<String>(db.getAllMeal().keySet());}
//

        breakfast_sp = (Spinner) findViewById(R.id.breakfast_sp);
        lunch_sp     = (Spinner) findViewById(R.id.lunch_sp);
        dinner_sp    = (Spinner) findViewById(R.id.dinner_sp);

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, igList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfast_sp.setAdapter(dataAdapter);
        lunch_sp.setAdapter(dataAdapter);
        dinner_sp.setAdapter(dataAdapter);



    }

    public void  linsterSetUp() {

        breakfast_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ggggg",igList.get(position));
                breakfast_position=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lunch_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lunch_position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dinner_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dinner_position=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void breakfastDoneBtnClick(View view){
        db.assignRecipeToPlanSlot(today,"Breakfast",igList.get(breakfast_position));


        igList = new ArrayList<String>(db.getAllMeal().keySet());
        dataAdapter.notifyDataSetChanged();
        dataAdapter.clear();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, igList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breakfast_sp.setAdapter(dataAdapter);

    }

    public void lunchDoneBtnClick(View view){

        db.assignRecipeToPlanSlot(today,"Lunch",igList.get(lunch_position));
    }

    public void dinnerDoneBtnClick(View view){

        db.assignRecipeToPlanSlot(today,"Dinner",igList.get(dinner_position));

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

