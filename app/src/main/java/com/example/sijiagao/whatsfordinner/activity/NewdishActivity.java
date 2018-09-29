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
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

public class NewdishActivity extends AppCompatActivity {
    public static final String TAG = "NewdishActivity";

    private TextView recipeNamePlainText = findViewById(R.id.recipeNamePlainText);
    private ImageView recipeImageImageView = findViewById(R.id.recipeImageImageView);
    private TextView ingredientNameTV1 = findViewById(R.id.ingredientNameTV1);
    private TextView ingredientTVQuantity1 = findViewById(R.id.ingredientQuantityTV1);
    private TextView ingredientUnitTV1 = findViewById(R.id.ingredientUnitTV1);
    private TextView ingredientNameTV2 = findViewById(R.id.ingredientNameTV2);
    private TextView ingredientTVQuantity2 = findViewById(R.id.ingredientQuantityTV2);
    private TextView ingredientUnitTV2 = findViewById(R.id.ingredientUnitTV2);
    private TextView ingredientNameTV3 = findViewById(R.id.ingredientNameTV3);
    private TextView ingredientTVQuantity3 = findViewById(R.id.ingredientQuantityTV3);
    private TextView ingredientUnitTV3 = findViewById(R.id.ingredientUnitTV3);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdish);

        ingredientTVQuantity1.setKeyListener(DigitsKeyListener.getInstance(true,true));
        ingredientTVQuantity2.setKeyListener(DigitsKeyListener.getInstance(true,true));
        ingredientTVQuantity3.setKeyListener(DigitsKeyListener.getInstance(true,true));


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


    // Buttons //
    public void clickAddImage(View view) {
        Log.i(TAG,"clickAddImage");

    }

    public void clickDone(View view) {
        Log.i(TAG,"clickDone");
        DatabaseHelper helper = DatabaseHelper.getInstance(this);
        String recipeName = recipeNamePlainText.toString();
        String recipeIamgeLoc = recipeImageImageView.toString();

        //Direction area goes here

        try {
            double ingredientQty1 = Double.parseDouble(ingredientTVQuantity1.toString());
            double ingredientQty2 = Double.parseDouble(ingredientTVQuantity2.toString());
            double ingredientQty3 = Double.parseDouble(ingredientTVQuantity3.toString());
            // quantity # 4~10 goes here
        } catch (NumberFormatException e) {
            Log.i(TAG, "error while parse double");
        }

        String ingredientName1 = ingredientNameTV1.toString();
        String ingredientName2 = ingredientNameTV2.toString();
        String ingredientName3 = ingredientNameTV3.toString();
        // Name 4-10 goes here
        String ingredientUnit1 = ingredientUnitTV1.toString();
        String ingredientUnit2 = ingredientUnitTV2.toString();
        String ingredientUnit3 = ingredientUnitTV3.toString();
        // ingredientUnit 4~10 goes here



       //tempRecipe.setRecipeName(recipeName);


    }




}
