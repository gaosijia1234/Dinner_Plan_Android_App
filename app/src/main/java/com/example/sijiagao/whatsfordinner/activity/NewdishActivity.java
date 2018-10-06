package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.provider.MediaStore;
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

    public static final int PICK_IMAGE = 100;
    Uri imageUri;



    ImageView recipeImageImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        recipeImageImageView = findViewById(R.id.recipeImageImageView);
        String recipeIamgeLoc = "image!!!";


        //String[] myIngredient = { "tomato","salt","pepper","tst","lamp","cool","whatever", "pc","mac"};
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        String[] myIngredient = db.getExistingIngredientList().toArray(new String[0]);

        //Create Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, myIngredient);

        /*int[] ingredientIDs = new int[] {R.id.ingredientNameTV1, R.id.ingredientNameTV2, R.id.ingredientNameTV3,
                R.id.ingredientNameTV4, R.id.ingredientNameTV5, R.id.ingredientNameTV6, R.id.ingredientNameTV7,
                R.id.ingredientNameTV8, R.id.ingredientNameTV9, R.id.ingredientNameTV10};
        for(int id: ingredientIDs){
            AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(id);
            acTextView.setThreshold(1);
            acTextView.setAdapter(adapter);
        }*/

        //Find TextView control, Set the number of characters the user must type before the drop down list is shown,
        //Set the adapter
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV1);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);
        AutoCompleteTextView acTextView2 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV2);
        acTextView2.setThreshold(1);
        acTextView2.setAdapter(adapter);
        AutoCompleteTextView acTextView3 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV3);
        acTextView3.setThreshold(1);
        acTextView3.setAdapter(adapter);
        AutoCompleteTextView acTextView4 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV4);
        acTextView4.setThreshold(1);
        acTextView4.setAdapter(adapter);
        AutoCompleteTextView acTextView5 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV5);
        acTextView5.setThreshold(1);
        acTextView5.setAdapter(adapter);
        AutoCompleteTextView acTextView6 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV6);
        acTextView6.setThreshold(1);
        acTextView6.setAdapter(adapter);
        AutoCompleteTextView acTextView7 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV7);
        acTextView7.setThreshold(1);
        acTextView7.setAdapter(adapter);
        AutoCompleteTextView acTextView8 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV8);
        acTextView8.setThreshold(1);
        acTextView8.setAdapter(adapter);
        AutoCompleteTextView acTextView9 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV9);
        acTextView9.setThreshold(1);
        acTextView9.setAdapter(adapter);
        AutoCompleteTextView acTextView10 = (AutoCompleteTextView) findViewById(R.id.ingredientNameTV10);
        acTextView10.setThreshold(1);
        acTextView10.setAdapter(adapter);


        TextView ingredientQuantityTV1 = findViewById(R.id.ingredientQuantityTV1);
        ingredientQuantityTV1.setKeyListener((DigitsKeyListener.getInstance(true,true)));
    }


    public void imageGalleryBtnClick(View view) {
        Log.i(TAG,"imageGalleryBtnClick");

        openGallery();
    }

    public void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            recipeImageImageView.setImageURI(imageUri);
        }
    }

    public void imageUrlBtnClick(View view) {
        Log.i(TAG,"imageUrlBtnClick");


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
           if (!tempRecipe.getRecipeName().matches("") && !helper.checkRecipeExistence(recipeName)) {

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
               String error = "";
               if(tempRecipe.getRecipeName().matches("")) error = "Nothing Added";
               if(helper.checkRecipeExistence(recipeName)) error = "Duplicated Recipe Name";
               Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
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
