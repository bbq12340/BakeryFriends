package com.example.bakingfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.content.res.Resources;

import java.util.ArrayList;

public class ResultActivity extends Activity {
    String bakery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String choice = intent.getStringExtra("choice"); // "cookie"
        ArrayList<String> myIngredient = getIntent().getStringArrayListExtra("myIngredient");
        Object[] myIngredientArray = myIngredient.toArray();


    }
}
