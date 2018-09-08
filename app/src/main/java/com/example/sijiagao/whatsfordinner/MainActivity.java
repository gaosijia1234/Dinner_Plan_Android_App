package com.example.sijiagao.whatsfordinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button recipeBtn = findViewById(R.id.recipeBtn);
        ImageButton recipeImageBtn = findViewById(R.id.recipeImageBtn);
        recipeImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(getApplicationContext(), RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });
    }
}
