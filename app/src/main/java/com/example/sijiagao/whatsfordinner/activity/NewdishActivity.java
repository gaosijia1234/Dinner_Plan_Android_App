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

//        TextView ingredientTextView1 = findViewById(R.id.ingredientTextView1);
//        TextView ingredientTVQuantity1 = findViewById(R.id.ingredientTVQuantity1);
//        TextView ingredientTVUnit1 = findViewById(R.id.ingredientTVUnit1);
//
//        TextView ingredientTextView2 = findViewById(R.id.ingredientTextView2);
//        TextView ingredientTVQuantity2 = findViewById(R.id.ingredientTVQuantity2);
//        TextView ingredientTVUnit2 = findViewById(R.id.ingredientTVUnit2);
//
//        String ingredient1 = ingredientTextView1.toString();
//        double ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.toString());
//        String ingredientUnit1 = ingredientTVUnit1.toString();
//
//        String ingredient2 = ingredientTextView2.toString();
//        double ingredientQty2 = Double.parseDouble(ingredientTVQuantity2.toString());
//        String ingredientUnit2 = ingredientTVUnit2.toString();



        String[] myIngredient = { "tomato","salt","pepper","tst"};

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientTextView1);
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
