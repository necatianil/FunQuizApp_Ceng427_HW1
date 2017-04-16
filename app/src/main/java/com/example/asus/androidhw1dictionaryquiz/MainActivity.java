package com.example.asus.androidhw1dictionaryquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void Training(View view){
        Intent intent = new Intent(this , TraningScreen.class);
        startActivity(intent);
    }
    public void AddWord(View view){
        Intent intent = new Intent(this , AddWordScreen.class);
        startActivity(intent);
    }
    public void Quiz(View view){
        Intent intent = new Intent(this , QuizScreen.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
