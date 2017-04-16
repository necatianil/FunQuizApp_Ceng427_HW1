package com.example.asus.androidhw1dictionaryquiz;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class AddWordScreen extends AppCompatActivity {
    EditText inputText,inputText2;
    Button addbutton;
    private String filename = "dict.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    private HashMap<String, String> dict;
    ArrayList<String> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word_screen);
        dict=new HashMap<>();
        WriteToFile();
    }
    public void WriteToFile(){
        inputText = (EditText) findViewById(R.id.editTextengword);
        inputText2 = (EditText) findViewById(R.id.editTextturword);
        myExternalFile = new File(getExternalFilesDir(filepath), filename);
        addbutton = (Button) findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //You can remove this command thing after add first word to dictionary
                    
                  /*  FileInputStream fis = new FileInputStream(myExternalFile);
                    DataInputStream in = new DataInputStream(fis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String strLine;
                    while ((strLine = br.readLine()) != null ) {
                        if(!((strLine).equals("\n"))) {
                            String[] word = strLine.split(":");
                            dict.put(word[0], word[1]);
                        }
                    }
                    in.close();*/
                    array = new ArrayList<>(dict.keySet());
                    if(!inputText.getText().toString().equals("") && !inputText2.getText().toString().equals("") && !(array.contains(inputText.getText().toString())) ){
                    FileOutputStream fos = new FileOutputStream(myExternalFile,true);
                    OutputStreamWriter osw=new OutputStreamWriter(fos);
                    osw.write(inputText.getText().toString()+":"+inputText2.getText().toString()+"\n");
                    osw.close();
                    Toast.makeText(AddWordScreen.this, "Added successfully!", Toast.LENGTH_SHORT).show();
                        }
                    else if( (array.contains(inputText.getText().toString()))){
                        Toast.makeText(AddWordScreen.this, "Word is already in Dictionary!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(AddWordScreen.this, "Please insert correctly!", Toast.LENGTH_SHORT).show();
                    }
                     inputText.setText("");
                     inputText2.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
