package com.example.sijiagao.whatsfordinner.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class NewdishActivity extends AppCompatActivity {
    public static final String TAG = "NewdishActivity";

     private Recipe tempRecipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);


    }


    // Buttons //
    public void clickAddImage(View view) {
        Log.i(TAG,"clickAddImage");

    }

    public void clickDone(View view) {
        Log.i(TAG,"clickDone");

        TextView recipeNamePlainText = findViewById(R.id.recipeNamePlainText);
        ImageView recipeImageImageView = findViewById(R.id.recipeImageImageView);
        TextView ingredientNameTV1 = findViewById(R.id.ingredientNameTV1);
        TextView ingredientTVQuantity1 = findViewById(R.id.ingredientQuantityTV1);
        TextView ingredientUnitTV1 = findViewById(R.id.ingredientUnitTV1);
        TextView ingredientNameTV2 = findViewById(R.id.ingredientNameTV2);
        TextView ingredientTVQuantity2 = findViewById(R.id.ingredientQuantityTV2);
        TextView ingredientUnitTV2 = findViewById(R.id.ingredientUnitTV2);
        TextView ingredientNameTV3 = findViewById(R.id.ingredientNameTV3);
        TextView ingredientTVQuantity3 = findViewById(R.id.ingredientQuantityTV3);
        TextView ingredientUnitTV3 = findViewById(R.id.ingredientUnitTV3);
        ingredientTVQuantity1.setKeyListener(DigitsKeyListener.getInstance(true,true));
        ingredientTVQuantity2.setKeyListener(DigitsKeyListener.getInstance(true,true));
        ingredientTVQuantity3.setKeyListener(DigitsKeyListener.getInstance(true,true));

        String recipeName = recipeNamePlainText.getText().toString();


        String recipeIamgeLoc = "image!!!";

        //Direction area goes here
        double ingredientQty1 =0;
        double ingredientQty2 =0;
        double ingredientQty3 =0;
        try {
            ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.getText().toString());
            ingredientQty2 = Double.parseDouble(ingredientTVQuantity2.getText().toString());
            ingredientQty3 = Double.parseDouble(ingredientTVQuantity3.getText().toString());
            // quantity # 4~10 goes here
        } catch (NumberFormatException e) {
            Log.i(TAG, "error while parse double");
        }

        String ingredientName1 = ingredientNameTV1.getText().toString();
        String ingredientName2 = ingredientNameTV2.getText().toString();
        String ingredientName3 = ingredientNameTV3.getText().toString();
        // Name 4-10 goes here
        String ingredientUnit1 = ingredientUnitTV1.getText().toString();
        String ingredientUnit2 = ingredientUnitTV2.getText().toString();
        String ingredientUnit3 = ingredientUnitTV3.getText().toString();
        // ingredientUnit 4~10 goes here


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


        List<Ingredient> listIngredient = new ArrayList<>();
        IngredientUnit tempIgUnit1 = new IngredientUnit(ingredientUnit1,ingredientQty1);
        IngredientUnit tempIgUnit2 = new IngredientUnit(ingredientUnit2,ingredientQty2);
        IngredientUnit tempIgUnit3 = new IngredientUnit(ingredientUnit3,ingredientQty3);
        Ingredient tempIg1 = new Ingredient(ingredientName1,tempIgUnit1);
        Ingredient tempIg2 = new Ingredient(ingredientName2,tempIgUnit2);
        Ingredient tempIg3 = new Ingredient(ingredientName3,tempIgUnit3);

        listIngredient.add(tempIg1);
        listIngredient.add(tempIg2);
        listIngredient.add(tempIg3);

        tempRecipe = new Recipe(recipeName,listIngredient,"direction!!!","image");

        DatabaseHelper helper = DatabaseHelper.getInstance(this);
        helper.addRecipe(tempRecipe);
        Log.i(TAG, "add recipe success");
        /*if (helper.checkRecipeExistence(tempRecipe.getRecipeName()))
        {  Log.i(TAG, "recipe already existed error ");
        }

        else {

        }*/




       //tempRecipe.setRecipeName(recipeName);


    }




}
