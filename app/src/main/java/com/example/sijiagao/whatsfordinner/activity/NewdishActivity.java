package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.ingredient.IngredientUnit;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class NewdishActivity extends AppCompatActivity {
    public static final String TAG = "NewdishActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        String[] myIngredient = { "tomato","salt","pepper","tst","lamp","cool","whatever", "pc","mac"};
        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV1);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);


        TextView ingredientQuantityTV1 = findViewById(R.id.ingredientQuantityTV1);
        ingredientQuantityTV1.setKeyListener((DigitsKeyListener.getInstance(true,true)));
    }


    // Buttons for add image to ImageView//
    public void clickAddImage(View view) {
        Log.i(TAG,"clickAddImage");
    }

  // Helper function to get all TextView in ViewGroup//
    public static void findViews(Context context, View v, ArrayList array){
        try {
               if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;
                   for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    findViews(context, child,array);
                }
            } else if (v instanceof TextView) {
                //Log.i(TAG,  ((TextView) v).getText().toString());
                if (!((TextView) v).getText().toString().matches(""))
                 { array.add(((TextView) v).getText().toString());}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Click Done Button to save data to DataBase //
    public void clickDone(View view) {
        Log.i(TAG,"clickDone");

        TextView recipeNamePlainText = findViewById(R.id.recipeNamePlainText);
        String recipeName = recipeNamePlainText.getText().toString();
        TextView recipeDirectionText = findViewById(R.id.recipeDirection);
        String recipeDirection = recipeDirectionText.getText().toString();

        ImageView recipeImageImageView = findViewById(R.id.recipeImageImageView);
        String recipeIamgeLoc = "image!!!";

        ArrayList<String> igList = new ArrayList<>();
        findViews(this, findViewById(R.id.linearLayout_newDish),igList);

        List<Ingredient> listIngredient = new ArrayList<>();
        for (int i=0 ; i<igList.size(); i +=3) {
            String name = igList.get(i);
            IngredientUnit tempUnit = new IngredientUnit(igList.get(i+2),
                                    Double.parseDouble(igList.get(i+1)));
            listIngredient.add( new Ingredient(name, tempUnit));
           }

           DatabaseHelper helper = DatabaseHelper.getInstance(this);
           Recipe tempRecipe= new Recipe(recipeName, listIngredient, recipeDirection, "image");
           if (!tempRecipe.getRecipeName().matches("")) {

               Log.i(TAG,"true or fasle is :"+ Boolean.toString(helper.checkRecipeExistence(recipeName)));
               helper.addRecipe(tempRecipe);
               Toast toast = Toast.makeText(this, "Recipe Added", Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
               toast.show();
               Log.i(TAG, "name is " + tempRecipe.getRecipeName());
               Log.i(TAG, "direction is " + tempRecipe.getCookingDirections());
               Log.i(TAG, "add recipe success");
             }
             else {
               Toast toast = Toast.makeText(this, "Nothing Added", Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
               toast.show();
           }

//      ingredientTVQuantity1.setKeyListener(DigitsKeyListener.getInstance(true,true));
//      ingredientTVQuantity2.setKeyListener(DigitsKeyListener.getInstance(true,true));
//      ingredientTVQuantity3.setKeyListener(DigitsKeyListener.getInstance(true,true));
//
//
//
//        //Direction area goes here
//        double ingredientQty1 =0;
//        double ingredientQty2 =0;
//        double ingredientQty3 =0;
//        try {
//            ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.getText().toString());
//            ingredientQty2 = Double.parseDouble(ingredientTVQuantity2.getText().toString());
//            ingredientQty3 = Double.parseDouble(ingredientTVQuantity3.getText().toString());
//            // quantity # 4~10 goes here
//        } catch (NumberFormatException e) {
//            Log.i(TAG, "error while parse double");
//        }
//


        /*if (helper.checkRecipeExistence(tempRecipe.getRecipeName()))
        {  Log.i(TAG, "recipe already existed error ");
        }

        else {

        }*/

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }




}
