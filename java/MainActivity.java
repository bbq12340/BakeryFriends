package com.example.bakingfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button btnStart, btnRead;
    ListView bakeryChoiceList;
    EditText Ingredient, dlgEdt;
    String [] bakeryChoice;
    String query, choice;
    TextView toastText;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnRead = (Button) findViewById(R.id.btnRead);
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("나만의 냉장고");
        dlg.setView(dialogView);

        dlgEdt = (EditText) dialogView.findViewById(R.id.edtMemo);


        dlg.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        try{
                            FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                            String str = dlgEdt.getText().toString();
                            outFs.write(str.getBytes());
                            outFs.close();
                        } catch (IOException e){}

                    }
                });

        dlg.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast toast = new Toast(MainActivity.this);

                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE))
                                .getDefaultDisplay();
                        toastView = (View) View.inflate(
                                MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView) toastView
                                .findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
        dlg.show();

        return false;
    }
}
