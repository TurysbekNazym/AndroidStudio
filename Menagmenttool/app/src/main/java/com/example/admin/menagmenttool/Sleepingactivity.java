package com.example.admin.menagmenttool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sleepingactivity extends AppCompatActivity {

    public EditText names;
    public EditText meaningone;
    public EditText meamingtwo;
    private DatabaseReference mDatabase;
    private DatabaseReference Sleep;
    Button btn;
    public List<Sleeping> list;
    String name;
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleepingactivity);

        names = (EditText) findViewById(R.id.sleep);
        meaningone = (EditText) findViewById(R.id.whatmean);
        meamingtwo = (EditText) findViewById(R.id.feelings);
        btn = (Button) findViewById(R.id.click);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle extras = getIntent().getExtras();
        name = extras.getString("idd");
        day = extras.getString("Message2");
        Sleep = mDatabase.child(name).child(day).child("Sleeping");
        list = new ArrayList<>();
  btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          String name1 = "";
          name1 = names.getText().toString();
          String name2 = "";
          name2 = meaningone.getText().toString();
          String name3 = "";
          name3 = meamingtwo.getText().toString();
          Sleeping r2 = new Sleeping(name1,name2,name3);
          list.add(r2);
          for (Sleeping journalEntry : list) {
              String key = Sleep.push().getKey();
              Sleep.child(key).setValue(journalEntry);

          }
      }
  });

        Sleep.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Sleeping note = noteSnapshot.getValue(Sleeping.class);
                    Sleeping p = new Sleeping(note.getTitle(), note.getMeans(), note.getDetailMean());
                   EditText text1 = (EditText) findViewById(R.id.sleep);
                   // Toast.makeText(getApplication(), p.getMeans(), Toast.LENGTH_LONG).show();
                    text1.setText(p.getTitle());
                    EditText text2 = (EditText) findViewById(R.id.whatmean);
                    text2.setText(p.getMeans());
                    EditText text3 = (EditText) findViewById(R.id.feelings);
                    text3.setText(p.getDetailMean());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Hey", databaseError.getMessage());
            }
        });

    }
}
