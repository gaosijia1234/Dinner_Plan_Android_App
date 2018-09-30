package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
            IngredientUnit tempUnit = new IngredientUnit(igList.get(i+2),Double.parseDouble(igList.get(i+1)));
            listIngredient.add( new Ingredient(name, tempUnit));
           }

           if (!tempRecipe.getRecipeName().matches("")) {
               tempRecipe = new Recipe(recipeName, listIngredient, recipeDirection, "image");
               DatabaseHelper helper = DatabaseHelper.getInstance(this);
               helper.addRecipe(tempRecipe);
               Log.i(TAG, "name is " + tempRecipe.getRecipeName());
               Log.i(TAG, "add recipe success");
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
//

        // ***************************************** Drop down menu function **************************************************

//        String[] myIngredient = { "tomato","salt","pepper","tst"};
//        //Create Array Adapter
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);
//        //Find TextView control
//        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV1);
//        //Set the number of characters the user must type before the drop down list is shown
//        acTextView.setThreshold(1);
//        //Set the adapter
//        acTextView.setAdapter(adapter);
        // *******************************************************************************************************************



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
