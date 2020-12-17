package com.example.bakingfriends;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
        setTitle("베이킹 프렌즈");

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
                try {
                    query = Ingredient.getText().toString();
                    choice = bakeryChoice[bakeryChoiceList.getCheckedItemPosition()].toString();
                    List<String> myIngredient = new ArrayList<String>(Arrays.asList(query.split("[-|,|\\s]")));
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("choice", choice);
                    intent.putStringArrayListExtra("myIngredient", (ArrayList<String>) myIngredient);
                    startActivity(intent);
                }catch (Exception e2){
                    Toast.makeText(MainActivity.this,"메뉴를 클릭해주세요",Toast.LENGTH_SHORT).show();}
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
        switch (item.getItemId()) {
            case R.id.itemDiary:
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                androidx.appcompat.app.AlertDialog.Builder dlg = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("나만의 냉장고 (갱신)");
                dlg.setIcon(R.drawable.icon_dialog);
                dlg.setView(dialogView);

                dlgEdt = (EditText) dialogView.findViewById(R.id.edtMemo);


                dlg.setPositiveButton("저장",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                try {
                                    FileOutputStream outFs = openFileOutput("file.txt", Context.MODE_PRIVATE);
                                    String str = dlgEdt.getText().toString();
                                    outFs.write(str.getBytes());
                                    outFs.close();
                                } catch (IOException e) { }
                                Toast.makeText(MainActivity.this,"저장하였습니다",Toast.LENGTH_SHORT).show();
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
                return true;

            case R.id.itemSave:
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte[] txt = new byte[500];
                    inFs.read(txt);
                    String str = new String(txt);
                    androidx.appcompat.app.AlertDialog.Builder dlg2 = new AlertDialog.Builder(MainActivity.this);
                    dlg2.setTitle("나만의 냉장고 (현재 재료)");
                    dlg2.setIcon(R.drawable.icon_dialog);
                    dlg2.setMessage(str);
                    dlg2.setPositiveButton("확인", null);
                    dlg2.show();
                    inFs.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "기록된 재료가 없습니다", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return false;
    }
}
