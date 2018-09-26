package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

public class NewdishActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        Resources resource = getResources();
//        Spinner spinner1 = findViewById(R.id.spinner1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                NewdishActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.newDishSpinner1));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        spinner1.setAdapter(spinnerAdapter);

        // recipe name
        TextView recipeNamePlainText = findViewById(R.id.recipeNamePlainText);
        String recipeName = recipeNamePlainText.toString();

        // recipe image
        ImageView recipeImageImageView = findViewById(R.id.recipeImageImageView);
        String recipeIamgeLoc = recipeImageImageView.toString();
        //System.out.println(recipeIamgeLoc);

        // recipe add btn
//        ImageButton recipeAddImageBtn = findViewById(R.id.recipeImageBtn);
//        recipeAddImageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        String[] myIngredient = { "tomato","salt","pepper"};

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientTextView);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);






    }


}
