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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

public class NewdishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        Resources resource = getResources();
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

        // **************************************** for drop down menu *****************************************************
        // name of the ingre
        TextView ingredientNameTV1 = findViewById(R.id.ingredientNameTV1);
        // quantity of the ingre
        TextView ingredientTVQuantity1 = findViewById(R.id.ingredientQuantityTV1);
        // unit of the ingre
        TextView ingredientUnitTV1 = findViewById(R.id.ingredientUnitTV1);

        String ingredientName1 = ingredientNameTV1.toString();
        try {
            double ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.toString());
        } catch (NumberFormatException e) {
            // ingredientQty1 did not contain a valid double
        }
        String ingredientUnit1 = ingredientUnitTV1.toString();


        String[] myIngredient = { "tomato","salt","pepper","tst"};

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV1);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);
        // *******************************************************************************************************************

        // button Done
        Button doneBtnNewDish = findViewById(R.id.doneBtnNewDish);
        doneBtnNewDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });






    }


}
