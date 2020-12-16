package com.example.baking_friends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    Button btnStart, btnEnter;
    EditText Ingredient;
    ListView listIng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Baking Friends");
    }






    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"나만의 냉장고");
        return true;
    }


}


