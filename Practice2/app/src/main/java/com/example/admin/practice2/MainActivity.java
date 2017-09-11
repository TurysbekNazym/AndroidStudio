package com.example.admin.practice2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.*;
import static com.example.admin.practice2.R.drawable.per2;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public RadioButton btn1,btn2,btn3;
    public ImageView img2,img3;
    public TextView text,text2,text3,text4,text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 =(RadioButton) findViewById(R.id.first);
        btn2 =(RadioButton) findViewById(R.id.second);
        btn3 =(RadioButton) findViewById(R.id.third);
        img2=(ImageView)    findViewById(R.id.image);
        text=(TextView) findViewById(R.id.txt);
        text2=(TextView) findViewById(R.id.status);
        img3=(ImageView)    findViewById(R.id.iconn);
        text3=(TextView) findViewById(R.id.money);
        text4=(TextView) findViewById(R.id.phone);
        text5=(TextView) findViewById(R.id.message);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        img2.setOnClickListener(this);
        text.setOnClickListener(this);
        text2.setOnClickListener(this);
        img3.setOnClickListener(this);
        text3.setOnClickListener(this);
        text4.setOnClickListener(this);
        btn1.setChecked(true);
        text5.setOnClickListener(this);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first:
                img2.setImageResource(R.drawable.per3);
                text.setText("Big bos!!!!");
                text2.setText("Available");
                img3.setImageResource(R.drawable.dot);
                text3.setText("$17.00");
                text4.setText("87021151269");
                text5.setText("Inactive");
                text2.setTextColor(rgb(50,205,50));
                btn1.setChecked(true);
                btn2.setChecked(false);
                btn3.setChecked(false);
                break;
            case R.id.second:
                 img2.setImageResource(R.drawable.per2);
                 text.setText("Jhon Broklyn");
                 text2.setText("Offline");
                text2.setTextColor(rgb(255,0,0));
                 img3.setImageResource(R.drawable.red);
                 text3.setText("$50.00");
                 text4.setText("87775677890");
                 text5.setText("Possible");
                 btn1.setChecked(false);
                 btn2.setChecked(true);
                 btn3.setChecked(false);
                break;
            case R.id.third:
                img2.setImageResource(R.drawable.per1);
                text.setText("Brad Pitt");
                text2.setText("Ongoing");
                text2.setTextColor(rgb(255,215,0));
                text3.setText("$30.00");
                text4.setText("87879806543");
                img3.setImageResource(R.drawable.yellow);
                text5.setText("active");
                btn1.setChecked(false);
                btn2.setChecked(false);
                btn3.setChecked(true);
                break;
        }
    }


}
