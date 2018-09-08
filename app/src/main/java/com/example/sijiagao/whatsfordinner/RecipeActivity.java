package com.example.sijiagao.whatsfordinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecipeActivity extends AppCompatActivity {

    ListView recipeListView;
    String[] recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

//        Resources resource = getResources();
        recipeListView = findViewById(R.id.recipeListView);
        recipes = getResources().getStringArray(R.array.recipes);

        // now we need to merge recipeListView(layout) and recipes(content) together
        recipeListView.setAdapter(new ArrayAdapter<String>(this, R.layout.recipe_listview_detail, recipes));
    }
}
