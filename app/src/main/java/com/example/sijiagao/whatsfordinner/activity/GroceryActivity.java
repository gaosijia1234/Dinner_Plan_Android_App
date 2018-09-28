package com.example.sijiagao.whatsfordinner.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.fragment.FragmentGrocey;

public class GroceryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
    }


    public void addClickHandler(View view) {

        //passing Arguments
        Bundle arguments = new Bundle();
        arguments.putString(FragmentGrocey.MESSAGE_KEY, "Message for Pass Argument");

        FragmentGrocey fragment = new FragmentGrocey();

        fragment.setArguments(arguments);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment1,fragment)
                .commit();

    }


    public void removeClickHandler(View view) {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment1);
         if ( fragment != null ) {
             getSupportFragmentManager()
                     .beginTransaction()
                     .remove(fragment)
                     .commit();
         }

    }
}
