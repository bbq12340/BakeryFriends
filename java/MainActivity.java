package com.example.baking_friends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    Button btnStart;
    ListView bakeryChoiceList;
    EditText Ingredient;
    String [] bakeryChoice;
    String query, choice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Baking Friends");
        
        btnStart = (Button) findViewById(R.id.btnStart);
        Ingredient = (EditText) findViewById(R.id.Ingredient);
        bakeryChoiceList = (ListView) findViewById(R.id.bakeryChoice);

        bakeryChoiceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.bakeryChoice,
                android.R.layout.simple_list_item_single_choice);
        bakeryChoiceList.setAdapter(adapter);
        bakeryChoice = getResources().getStringArray(R.array.bakeryChoice);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                query = Ingredient.getText().toString();
                choice = bakeryChoice[bakeryChoiceList.getCheckedItemPosition()].toString();
                List<String> myIngredient = new ArrayList<String>(Arrays.asList(query.split(",")));
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("choice", choice);
                intent.putStringArrayListExtra("myIngredient", (ArrayList<String>) myIngredient);
                startActivity(intent);
            }
        });
    }






    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"나만의 냉장고");
        return true;
    }


}


