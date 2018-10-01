package com.example.sijiagao.whatsfordinner.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sijiagao.whatsfordinner.R;
import com.example.sijiagao.whatsfordinner.fragment.RecipeDetailFragment;

public class RecipeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

//       Resources resource = getResources();
//       recipeListView = findViewById(R.id.recipeListView);
//       recipes = getResources().getStringArray(R.array.recipes);
//
//        // now we need to merge recipeListView(layout) and recipes(content) together
//        recipeListView.setAdapter(new ArrayAdapter<String>(this, R.layout.recipe_listview_detail, recipes));


       // RecipeDetailFragment fragment = RecipeDetailFragment.newInstance("Pass message from GroceryActivity");






        RecipeDetailFragment fragment = new RecipeDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.detailFragment,fragment)
                .commit();




        //DatabaseHelper helper = DatabaseHelper.getInstance(this);
       // Log.i("test", helper.getRecipeByName("smartwater").toString());

    }


//    public void addClickHandler(View view) {
//        RecipeDetailFragment fragment = RecipeDetailFragment.newInstance("Pass message from GroceryActivity");
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.fragment1,fragment)
//                .commit();
//    }
//
//
//    public void removeClickHandler(View view) {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment1);
//        if ( fragment != null ) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .remove(fragment)
//                    .commit();
//        }
//    }

}
