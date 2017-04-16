package com.example.asus.androidhw1dictionaryquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Handler;

public class QuizScreen extends AppCompatActivity {

    TextView questiontext,scoretext;
    Button answer;
    RadioButton rd1,rd2,rd3,rd4;
    RadioGroup rg;
    Random rd;
    int randomnumber=0,numofClick=0,i=0;
    double score=0;
    String value="";
    ArrayList<RadioButton> radioArray;
    ArrayList<Integer> randomnumberarray;
    private String filename = "dict.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    ArrayList<String> array;
    private HashMap<String, String> dict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);
        scoretext=(TextView)findViewById(R.id.scoretext);
        answer=(Button)findViewById(R.id.answerbutton);
        rd1=(RadioButton)findViewById(R.id.radioButton);
        rd2=(RadioButton)findViewById(R.id.radioButton2);
        rd3=(RadioButton)findViewById(R.id.radioButton3);
        rd4=(RadioButton)findViewById(R.id.radioButton4);
        radioArray=new ArrayList<RadioButton>();
        radioArray.add(rd1);
        radioArray.add(rd2);
        radioArray.add(rd3);
        radioArray.add(rd4);
        rd=new Random();
        randomnumberarray=new ArrayList<>();
        rg = (RadioGroup)findViewById(R.id.Radiogrp);
        ReadFromFile();
        generateChoise();

    }
    public void ReadFromFile(){
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
        questiontext=(TextView)findViewById(R.id.questiontext);
        dict=new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(myExternalFile);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null ) {
                if(!((strLine).equals("\n"))) {
                    String[] word = strLine.split(":");
                    dict.put(word[0], word[1]);
                }

            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        array = new ArrayList<>(dict.keySet());
        Collections.shuffle(array);


    }
    public void generateChoise(){
        Collections.shuffle(radioArray);
        questiontext.setText(array.get(numofClick));
        randomnumber=rd.nextInt(array.size());
      //  randomnumberarray.add(randomnumber);
        randomnumber=numofClick;
        i=0;
        while(i<4){
            if((numofClick == randomnumber) && !(randomnumberarray.contains(randomnumber))){
                radioArray.get(i).setText(dict.get(array.get(numofClick)));
                randomnumberarray.add(randomnumber);
                randomnumber=rd.nextInt(array.size()-1);
                i++;
            }
            else{
                if(!(randomnumberarray.contains(randomnumber))) {
                    radioArray.get(i).setText(dict.get(array.get(randomnumber)));
                    randomnumberarray.add(randomnumber);
                    randomnumber=rd.nextInt(array.size());
                    i++;
                }
                else{
                    randomnumber=rd.nextInt(array.size());
                }

            }
        }

    }
    public void calculateScore(View view){

       if(rg.getCheckedRadioButtonId()!=-1) {
            value = ((RadioButton) findViewById(rg.getCheckedRadioButtonId())).getText().toString();
            if (value.equals(dict.get(array.get(numofClick)))) {
                score++;
                scoretext.setText("Score : " + score);
                numofClick++;
                answer.setText("✔");
                answer.setTextColor(Color.parseColor("#ff000000"));
                answer.setBackgroundColor(Color.GREEN);
                randomnumberarray.clear();
                new CountDownTimer(1000,100){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                    answer.setText("Answer !");
                    answer.setTextColor(Color.parseColor("#ffffff"));
                    answer.setBackgroundColor(Color.parseColor("#fd330276"));
                    }
                }.start();

            } else {
                score = score - 0.5;
                scoretext.setText("Score :  " + score);
                numofClick++;
                answer.setText("✖");
                answer.setBackgroundColor(Color.RED);
                answer.setTextColor(Color.parseColor("#ff000000"));
                randomnumberarray.clear();
                new CountDownTimer(1000,100){

                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        answer.setText("Answer !");
                        answer.setTextColor(Color.parseColor("#ffffff"));
                        answer.setBackgroundColor(Color.parseColor("#fd330276"));
                    }
                }.start();

            }
            if (numofClick < array.size()) {
                generateChoise();
            }
            else {
                Intent intent=new Intent(QuizScreen.this,ScoreScreen.class);
                intent.putExtra("Score",score);
                startActivity(intent);
            }
        }
        else{
           Toast.makeText(this, "Please select your answer", Toast.LENGTH_SHORT).show();
       }

        rg.clearCheck();

    }
}
