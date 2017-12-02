package com.example.admin.menagmenttool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recipesPage extends AppCompatActivity {

    public EditText nameRecipe;
    public EditText ingredients;
    public EditText recipts;
    public Button btn;
    private DatabaseReference mDatabase;
    private DatabaseReference writeRecipt;
    public List<Recipe> list;
    public Boolean flag = false;
    String name;
    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_page);

        nameRecipe = (EditText) findViewById(R.id.nameRes);
        ingredients = (EditText) findViewById(R.id.ingredients);
        recipts  = (EditText) findViewById(R.id.recipe);
        btn = (Button) findViewById(R.id.keeps);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Bundle extras = getIntent().getExtras();

        name = extras.getString("idd");
        day = extras.getString("Message2");
        writeRecipt = mDatabase.child(name).child(day).child("Recipe");
        list = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name1 = "";
                name1 = nameRecipe.getText().toString();
                String name2 = "";
                name2 = ingredients.getText().toString();
                String name3 = "";
                name3 = recipts.getText().toString();
                Recipe r1 = new Recipe(name1,name2,name3);
                list.add(r1);
                for (Recipe journalEntry : list) {
                    String key = writeRecipt.push().getKey();
                    writeRecipt.child(key).setValue(journalEntry);
                    flag = true;
                }
            }
        });

        writeRecipt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    Recipe note = noteSnapshot.getValue(Recipe.class);
                    Recipe p = new Recipe(note.getName(),note.getIngredient(),note.getRecipts());
                    EditText text1 = (EditText) findViewById(R.id.nameRes);
                    text1.setText(p.getName());
                    EditText text2 = (EditText) findViewById(R.id.ingredients);
                    text2.setText(p.getIngredient());
                    EditText text3 = (EditText) findViewById(R.id.recipe);
                    text3.setText(p.getRecipts());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Hey", databaseError.getMessage());
            }
        });
    }

}
