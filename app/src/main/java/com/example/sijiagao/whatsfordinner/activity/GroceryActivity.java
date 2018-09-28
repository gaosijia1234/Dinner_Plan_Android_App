package com.example.sijiagao.whatsfordinner.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.fragment.FragmentGrocey;

public class GroceryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);

        FragmentGrocey fragment = new FragmentGrocey();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment1,fragment)
                .commit();
    }
}
