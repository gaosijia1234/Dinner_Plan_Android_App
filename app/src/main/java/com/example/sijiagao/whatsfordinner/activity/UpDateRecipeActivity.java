package com.example.sijiagao.whatsfordinner.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.database.DatabaseHelper;
import com.example.sijiagao.whatsfordinner.model.ingredient.Ingredient;
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;
import java.util.List;

public class UpDateRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_recipe);
        setUpExistContent();

    }


    public void setUpExistContent() {
        String rpName = getIntent().getStringExtra("name");
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        Recipe myCurrentRecipt = db.getRecipeByName(rpName);

        TextView recipeNamePlainText = findViewById(R.id.update_rpName);
        recipeNamePlainText.setText(myCurrentRecipt.getRecipeName());
        TextView recipeDirectionText = findViewById(R.id.update_direction);
        recipeDirectionText.setText(myCurrentRecipt.getCookingDirections());

        ArrayList<TextView> tvList = new ArrayList<>();
        List<Ingredient> igList =  myCurrentRecipt.getIngredients();
        findViews(this, findViewById(R.id.update_iglist),tvList);

        for (int i =0, j=0 ;i< igList.size() ; i++, j+=3){
            tvList.get(j).setText(igList.get(i).getIngredientName());
            tvList.get(j+1).setText(Double.toString(igList.get(i).getUnit().getQuantity()));
            tvList.get(j+2).setText(igList.get(i).getUnit().getUnitName());
        }
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
                 array.add(((TextView) v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
