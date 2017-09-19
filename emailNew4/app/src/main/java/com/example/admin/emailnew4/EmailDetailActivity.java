package com.example.admin.emailnew4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class EmailDetailActivity extends AppCompatActivity {

    ArrayList<String> name_sender=new ArrayList<String>();
    ArrayList<String> name_title=new ArrayList<String>();
    ArrayList<String> name_subject=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_detail);
        Button btn = (Button)findViewById(R.id.button);
        TextView sender_name = (TextView)findViewById(R.id.senderId);
        TextView title_name  = (TextView)findViewById(R.id.titleId);
        TextView subject_title = (TextView) findViewById(R.id.subject);
        btn.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(EmailDetailActivity.this,EmailListActivity.class);
                startActivity(intent);
            }
        });
        Scanner scan = new Scanner(getResources().openRawResource((R.raw.sender)));
        Scanner scan1 = new Scanner(getResources().openRawResource(R.raw.title));
        Scanner scan2 = new Scanner(getResources().openRawResource(R.raw.subjectt));


        while(scan.hasNextLine()){
            String line = scan.nextLine();
            name_sender.add(line);

        }
        scan.close();
       int j = 0;
        while (scan1.hasNextLine()){
            String string = scan1.nextLine();
            name_title.add(string);

        }
        scan1.close();
          while (scan2.hasNextLine()){
            String string = scan2.nextLine();
            name_subject.add(string);

        }
        scan2.close();
        Bundle extras = getIntent().getExtras();
        int san = extras.getInt("message");
        //int san = Int.parseInt(s);
        //int san = getIntent().getIntExtra("message", 0);
        sender_name.setText(name_sender.get(san));
        title_name.setText(name_title.get(san));
        subject_title.setText(name_subject.get(san));


    }


}
