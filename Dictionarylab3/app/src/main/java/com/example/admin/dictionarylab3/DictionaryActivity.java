package com.example.admin.dictionarylab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DictionaryActivity extends AppCompatActivity  {

    public TextView textView;
    String[] words = new String[10];
    String[] difinition = new String[10];
    int cnt = 0,i = 0,j = 0,indx = 0;
    public ListView listt;
    String randomString = "";
    public void rundomFunction(){
     indx = new Random().nextInt(words.length);
     randomString = words[indx];

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        textView = (TextView) findViewById(R.id.txt);
        //textView.setOnClickListener(this);
        listt = (ListView) findViewById(R.id.listOfWords);

        Scanner s = new Scanner(getResources().openRawResource(R.raw.text));
        String str = " ";

        while(s.hasNextLine()){
          String line = s.nextLine();
            cnt++;
            if(cnt%2!=0) {
                words[i] = line;
                i++;
            }else {
                difinition[j] = line;
                j++;
            }

          }
          s.close();

        final ListView list = (ListView) findViewById(R.id.listOfWords);
    /*    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,words);
        list.setAdapter(adapter);

       listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(getBaseContext(),difinition[position],Toast.LENGTH_SHORT).show();
            }
        });*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,difinition);
        list.setAdapter(adapter);

        rundomFunction();
        textView.setText(randomString);
      //listt turgan
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(indx ==position) {
                   Toast.makeText(getBaseContext(), "correct!!!!", Toast.LENGTH_SHORT).show();
               }else
                   Toast.makeText(getBaseContext(), "incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rundomFunction();
                textView.setText(randomString);

            }
        });
       // textView.setOnClickListener();
     }

}





