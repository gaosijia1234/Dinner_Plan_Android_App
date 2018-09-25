package com.example.sijiagao.whatsfordinner.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sijiagao.whatsfordinner.R;

public class NewdishActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        Resources resource = getResources();
        Spinner spinner1 = findViewById(R.id.spinner1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                NewdishActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.newDishSpinner1));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(spinnerAdapter);
    }

}
