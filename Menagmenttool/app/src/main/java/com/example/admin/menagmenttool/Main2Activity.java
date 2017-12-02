package com.example.admin.menagmenttool;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.admin.menagmenttool.MyView.CustomView;

public class Main2Activity extends AppCompatActivity {

    private CustomView mCustomView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mCustomView = (CustomView)findViewById(R.id.custom_view);

    }
    public void clearCanvas(View v){
        mCustomView.clearCanvas();
    }




}
