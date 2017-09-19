package com.example.admin.emailnew4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.admin.emailnew4.R.id.parent;

import static com.example.admin.emailnew4.R.styleable.View;


public class EmailListActivity extends AppCompatActivity{
   RadioButton btn1,btn2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_list);

        final DetailsAdapter adapter = new DetailsAdapter(this,generateData());
        ListView listView = (ListView) findViewById(R.id.listId);
        listView.setAdapter(adapter);

         btn1 = (RadioButton) findViewById(R.id.radioButton);
         btn2 = (RadioButton) findViewById(R.id.radioButton2);

         btn1.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v){
             btn1.setChecked(true);
                btn2.setChecked(false);


            }
        });

        btn2.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v){
                btn2.setChecked(true);
                btn1.setChecked(false);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent intent = new Intent(EmailListActivity.this,EmailDetailActivity.class);
                intent.putExtra("message",position);
                startActivity(intent);
                //Toast.makeText(getBaseContext(), position+"mama", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private ArrayList<Details> generateData(){
        ArrayList<Details> detail = new ArrayList<Details>();
        detail.add(new Details(R.drawable.a,"Amigo","Come Online","Sep 19"));
        detail.add(new Details(R.drawable.a,"Amigo","Do it know","Sep 16"));
        detail.add(new Details(R.drawable.a,"Amigo","Login rename","Sep 13"));
        detail.add(new Details(R.drawable.a,"Amigo","Online Registration","Sep 11"));
        detail.add(new Details(R.drawable.c,"CodeFoces","Round 407","Sep 19"));
        detail.add(new Details(R.drawable.c,"CodeFoces","Round 488","Sep 17"));
        detail.add(new Details(R.drawable.c,"CodeFoces","Round 455","Sep 14"));
        detail.add(new Details(R.drawable.c,"CodeFoces","Round 90","Sep 12"));

        return  detail;
    }


}
