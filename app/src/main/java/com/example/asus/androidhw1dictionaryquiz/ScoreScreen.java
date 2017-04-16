package com.example.asus.androidhw1dictionaryquiz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreScreen extends AppCompatActivity {
    TextView suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        TextView scoretable=(TextView)findViewById(R.id.ScoreText);
        suggestion=(TextView)findViewById(R.id.suggestiontext);
        scoretable.setText("Your Final Score is : "+getIntent().getExtras().getDouble("Score"));
        control();
    }


    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void control(){
        if(getIntent().getExtras().getDouble("Score")<0){
            suggestion.setText("You have to make more practise !!");
            Toast.makeText(ScoreScreen.this, "Training Part is opening!", Toast.LENGTH_SHORT).show();
            new CountDownTimer(3000,100){
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                Intent intent=new Intent(ScoreScreen.this,TraningScreen.class);
                startActivity(intent);
                }
            }.start();
        }

    }
}
