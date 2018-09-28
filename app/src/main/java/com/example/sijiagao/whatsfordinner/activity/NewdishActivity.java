package com.example.sijiagao.whatsfordinner.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
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

        // recipe "add" button
        Button recipeAddBtn = findViewById(R.id.recipeAddBtn);
        recipeAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // recipe "Done" button
        Button recipeDoneBtn = findViewById(R.id.recipeDoneBtn);
        recipeDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // **************************************** drop down menu variables *****************************************************
        // name of the ingre
        TextView ingredientNameTV1 = findViewById(R.id.ingredientNameTV1);
        // quantity of the ingre
        TextView ingredientTVQuantity1 = findViewById(R.id.ingredientQuantityTV1);
        // to make sure that only one decimal point can be entered, e.g. 1.2 is ok, 1..2 is not ok
        ingredientTVQuantity1.setKeyListener(DigitsKeyListener.getInstance(true,true));

        //2
        TextView ingredientNameTV2 = findViewById(R.id.ingredientNameTV2);
        TextView ingredientTVQuantity2 = findViewById(R.id.ingredientQuantityTV2);
        ingredientTVQuantity2.setKeyListener(DigitsKeyListener.getInstance(true,true));

        //3
        TextView ingredientNameTV3 = findViewById(R.id.ingredientNameTV3);
        TextView ingredientTVQuantity3 = findViewById(R.id.ingredientQuantityTV3);
        ingredientTVQuantity3.setKeyListener(DigitsKeyListener.getInstance(true,true));

        // *************** change the TextViews to Strings and doubles **************************
        // unit of the ingre
        // 1
        TextView ingredientUnitTV1 = findViewById(R.id.ingredientUnitTV1);
        String ingredientName1 = ingredientNameTV1.toString();
        try {
            double ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.toString());
        } catch (NumberFormatException e) {
            // ingredientQty1 did not contain a valid double
        }
        String ingredientUnit1 = ingredientUnitTV1.toString();

        // 2
        TextView ingredientUnitTV2 = findViewById(R.id.ingredientUnitTV2);
        String ingredientName2 = ingredientNameTV2.toString();
        try {
            double ingredientQty2 = Double.parseDouble(ingredientTVQuantity2.toString());
        } catch (NumberFormatException e) {
        }
        String ingredientUnit2 = ingredientUnitTV2.toString();

        //3
        TextView ingredientUnitTV3 = findViewById(R.id.ingredientUnitTV3);
        String ingredientName3 = ingredientNameTV3.toString();
        try {
            double ingredientQty3 = Double.parseDouble(ingredientTVQuantity3.toString());
        } catch (NumberFormatException e) {
        }
        String ingredientUnit3 = ingredientUnitTV3.toString();

        // ***************************************** Drop down menu function **************************************************

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

    }


}
