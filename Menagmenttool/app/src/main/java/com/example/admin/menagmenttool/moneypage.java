package com.example.admin.menagmenttool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class moneypage extends AppCompatActivity {

    public EditText text1,text2,text3,money1,money2,money3;
    public TextView screen;
    public Button btn;
    private DatabaseReference mDatabase;
    private DatabaseReference SpendMoney;
    public List<InfoMoney> list;
    public List<InfoMoney> list2;
    public Boolean flag = false;
    String day;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moneypage);
        text1 = (EditText) findViewById(R.id.title1);
        text2 = (EditText) findViewById(R.id.title2);
        text3 = (EditText) findViewById(R.id.title3);
        money1 = (EditText) findViewById(R.id.cost1);
        money2 = (EditText) findViewById(R.id.cost2);
        money3 = (EditText) findViewById(R.id.cost3);
        btn = (Button) findViewById(R.id.sum);
        screen = (TextView) findViewById(R.id.total);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle extras = getIntent().getExtras();

        name = extras.getString("idd");
        day = extras.getString("Message2");

        SpendMoney = mDatabase.child(name).child(day).child("Spending");
        list = new ArrayList<>();
        list2 = new ArrayList<>();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 0;
                a = Integer.parseInt(money1.getText().toString());
                int b = 0;
                b = Integer.parseInt(money2.getText().toString());
                int c = 0;
                c = Integer.parseInt(money3.getText().toString());
                int sum = a + b + c;
                String s = sum + " ";
                String name1 = "";
                name1 = text1.getText().toString();
                String name2 = "";
                name2 = text2.getText().toString();
                String name3 = "";
                name3 = text3.getText().toString();
                InfoMoney m1 = new InfoMoney(name1, a);
                InfoMoney m2 = new InfoMoney(name2, b);
                InfoMoney m3 = new InfoMoney(name3, c);
                list.add(m1);
                list.add(m2);
                list.add(m3);
                screen.setText(s);
                for (InfoMoney journalEntry : list) {
                    String key = SpendMoney.push().getKey();
                    SpendMoney.child(key).setValue(journalEntry);
                    flag = true;
                }
            }
        });

          //  Toast.makeText(moneypage.this, "Saved", Toast.LENGTH_LONG).show();
            SpendMoney.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    int cnt = 0;
                    for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                        InfoMoney note = noteSnapshot.getValue(InfoMoney.class);
                        InfoMoney p = new InfoMoney(note.getName(),note.getCost());
                        list2.add(p);
                        if(cnt==0) {
                            EditText text1 = (EditText) findViewById(R.id.title1);
                            text1.setText(p.getName());
                            EditText money1 = (EditText) findViewById(R.id.cost1);
                            money1.setText(p.getCost()+"");
                        }
                        if(cnt==1) {
                            EditText text2 = (EditText) findViewById(R.id.title2);
                            text2.setText(p.getName());
                            EditText money2 = (EditText) findViewById(R.id.cost2);
                            money2.setText(p.getCost()+"");
                        }
                        if(cnt==2) {
                            EditText text3 = (EditText) findViewById(R.id.title3);
                            text3.setText(p.getName());
                            EditText money3 = (EditText) findViewById(R.id.cost3);
                            money3.setText(p.getCost()+"");
                        }
                        cnt++;


                       // money1.setText(list2.get(0).getCost());
                       //  function(p.getName(),p.getCost());

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d("Hey", databaseError.getMessage());
                }
            });

       // Toast.makeText(moneypage.this, list2.size()+"size", Toast.LENGTH_LONG).show();


    }
    public void function(String a,int b){
        Toast.makeText(moneypage.this, a+"size", Toast.LENGTH_LONG).show();
     text1.setText(a);
        money1.setText(b);
    }







}
