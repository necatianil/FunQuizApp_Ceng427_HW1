package com.example.asus.androidhw1dictionaryquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TraningScreen extends AppCompatActivity {

    TextView textturword,textengword;
    private String filename = "dict.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    ArrayList<String> array;
    private HashMap<String, String> dict;
    static int numofClick=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traning_screen);
        Toast.makeText(this, "Press anywhere in the screen to change the word", Toast.LENGTH_SHORT).show();
        ReadFromFile();

    }
    public void ReadFromFile(){
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
        textengword = (TextView)findViewById(R.id.textengword);
        textturword = (TextView)findViewById(R.id.textturword);
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
                textengword.setText(array.get(0));
                textturword.setText(dict.get(array.get(0)));

    }
    public void ChangeMethod(View v){
        numofClick++;
        if(numofClick<array.size()){
            textengword.setText(array.get(numofClick));
            textturword.setText(dict.get(array.get(numofClick)));
        }
        else{
            numofClick=0;
            textengword.setText(array.get(numofClick));
            textturword.setText(dict.get(array.get(numofClick)));
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(TraningScreen.this,MainActivity.class);
        startActivity(intent);

    }
}
