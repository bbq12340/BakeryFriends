package com.example.bakingfriends;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultActivity extends Activity {

    int arrayId;
    String [] bakery; // 베이커리 종류
    String [] bakeryIngredient; // 선택된 베이커리 재
    double [] countScore; // 재료 일치 점수
    int [] rank; // 베이커리 재료 일치 순위
    TextView recommend1, recommend2, recommend3;
    double first, second, third;
    int r1, r2, r3;
    ImageButton recommendImg1, recommendImg2, recommendImg3;
    View dialogView;
    ImageView dlgImg;
    TextView recipeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String choice = intent.getStringExtra("choice"); // "cookie"
        ArrayList<String> myIngredient = getIntent().getStringArrayListExtra("myIngredient");
        Object[] myIngredientArray = myIngredient.toArray();


        arrayId = this.getResources().getIdentifier(choice, "array", this.getPackageName());
        bakery = this.getResources().getStringArray(arrayId);
        countScore = new double [bakery.length];
        for (int i=0; i<bakery.length; i++) {
            int bakeryId = this.getResources().getIdentifier(bakery[i], "array", this.getPackageName());
            bakeryIngredient = this.getResources().getStringArray(bakeryId);
            for (int j=0; j<myIngredientArray.length; j++) {
                try {
                    if (Arrays.asList(bakeryIngredient).contains(myIngredientArray[j])) {
                        countScore[i]++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(bakery[i]);
                }
            }
            countScore[i] = countScore[i]/myIngredientArray.length;
        }


        recommend1 = (TextView) findViewById(R.id.recommend1);
        recommend2 = (TextView) findViewById(R.id.recommend2);
        recommend3 = (TextView) findViewById(R.id.recommend3);

        recommendImg1 = (ImageButton) findViewById((R.id.recommendImg1));
        recommendImg2 = (ImageButton) findViewById((R.id.recommendImg2));
        recommendImg3 = (ImageButton) findViewById((R.id.recommendImg3));

        first=0.0;
        second=0.0;
        third=0.0;
        for (int i=0; i<countScore.length; i++) {
            if (countScore[i]>first) {
                third = second;
                second = third;
                first = countScore[i];
                r1=i;
            } else if (countScore[i]>second) {
                third = second;
                second = countScore[i];
                r2=i;
            } else if (countScore[i]>third) {
                third=countScore[i];
                r3=i;
            }
        }
        recommend1.setText(this.getResources().getStringArray(
                this.getResources().getIdentifier(bakery[r1], "array", this.getPackageName()))[0]);
        recommend2.setText(this.getResources().getStringArray(
                this.getResources().getIdentifier(bakery[r2], "array", this.getPackageName()))[0]);
        recommend3.setText(this.getResources().getStringArray(
                this.getResources().getIdentifier(bakery[r3], "array", this.getPackageName()))[0]);

        final int r1Id = this.getResources().getIdentifier(bakery[r1], "drawable", this.getPackageName());
        final int r2Id = this.getResources().getIdentifier(bakery[r2], "drawable", this.getPackageName());
        final int r3Id = this.getResources().getIdentifier(bakery[r3], "drawable", this.getPackageName());
        recommendImg1.setImageResource(r1Id);
        recommendImg2.setImageResource(r2Id);
        recommendImg3.setImageResource(r3Id);

        recommendImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(ResultActivity.this, R.layout.dialog2, null);
                dlgImg = (ImageView) dialogView.findViewById(R.id.dlgImg);
                recipeTxt = (TextView) dialogView.findViewById(R.id.recipeTxt);
                try{
                    dlgImg.setImageResource(r1Id);
                } catch (NullPointerException e) {
                    System.out.println(r1Id);
                }
                recipeTxt.setText("레시피 준비 중...");
                AlertDialog.Builder dlg = new AlertDialog.Builder(ResultActivity.this);
                dlg.setTitle("레시피");
                dlg.setIcon(R.drawable.ico_baking_friends);
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기", null);
                dlg.show();
            }
        });
        recommendImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(ResultActivity.this, R.layout.dialog2, null);
                dlgImg = (ImageView) dialogView.findViewById(R.id.dlgImg);
                recipeTxt = (TextView) dialogView.findViewById(R.id.recipeTxt);
                try{
                    dlgImg.setImageResource(r2Id);
                } catch (NullPointerException e) {
                    System.out.println(r2Id);
                }
                recipeTxt.setText("레시피 준비 중...");
                AlertDialog.Builder dlg = new AlertDialog.Builder(ResultActivity.this);
                dlg.setTitle("레시피");
                dlg.setIcon(R.drawable.ico_baking_friends);
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기", null);
                dlg.show();
            }
        });
        recommendImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(ResultActivity.this, R.layout.dialog2, null);
                dlgImg = (ImageView) dialogView.findViewById(R.id.dlgImg);
                recipeTxt = (TextView) dialogView.findViewById(R.id.recipeTxt);
                try{
                    dlgImg.setImageResource(r3Id);
                } catch (NullPointerException e) {
                    System.out.println(r3Id);
                }
                recipeTxt.setText("레시피 준비 중...");
                AlertDialog.Builder dlg = new AlertDialog.Builder(ResultActivity.this);
                dlg.setTitle("레시피");
                dlg.setIcon(R.drawable.ico_baking_friends);
                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기", null);
                dlg.show();
            }
        });
    }
}
