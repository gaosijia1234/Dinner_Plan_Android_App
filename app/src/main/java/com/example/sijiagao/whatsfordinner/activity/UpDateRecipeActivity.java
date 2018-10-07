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
import com.example.sijiagao.whatsfordinner.model.recipe.Recipe;

import java.util.ArrayList;

public class UpDateRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_recipe);

        String rpName = getIntent().getStringExtra("name");
        DatabaseHelper db = DatabaseHelper.getInstance(this);
        Recipe myCurrentRecipt = db.getRecipeByName(rpName);

        ArrayList<TextView> tvList = new ArrayList<>();
        //findViews(this, findViewById(R.id.linearLayout_newDish),tvList);
        Log.i("update",rpName);


        TextView recipeNamePlainText = findViewById(R.id.update_rpName);
        recipeNamePlainText.setText(myCurrentRecipt.getRecipeName());
        TextView recipeDirectionText = findViewById(R.id.update_direction);
        recipeDirectionText.setText(myCurrentRecipt.getCookingDirections());



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
                 array.add(((TextView) v).getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
