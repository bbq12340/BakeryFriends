package com.example.bakingfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultActivity extends Activity {

    int arrayId;
    String [] bakery; // 베이커리 종류
    String [] bakeryIngredient; // 선택된 베이커리 재
    int [] countScore; // 재료 일치 점수
    int [] rank; // 베이커리 재료 일치 순위
    int max, t;
    TextView recommend1, recommend2, recommend3;

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
        countScore = new int [bakery.length];
        for (int i=0; i<bakery.length; i++) {
            int bakeryId = this.getResources().getIdentifier(bakery[i], "array", this.getPackageName());
            bakeryIngredient = this.getResources().getStringArray(bakeryId);
            for (int j=0; j<myIngredientArray.length; j++) {
                try {
                    if (Arrays.asList(bakeryIngredient).contains(myIngredientArray[j])) {
                        countScore[i]++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ;
                }
            }
        }

        recommend1 = (TextView) findViewById(R.id.recommend1);
        recommend2 = (TextView) findViewById(R.id.recommend2);
        recommend3 = (TextView) findViewById(R.id.recommend3);

        max=0;
        for (int i=0; i<countScore.length; i++) {
            if (countScore[i]>max) {
                max = countScore[i];
                t=i;
            }
        }
        recommend1.setText(bakery[t]);
    }
}
