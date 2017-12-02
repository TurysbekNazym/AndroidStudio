package com.example.admin.menagmenttool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    public EditText user_name;
    public  EditText user_email;
    public EditText user_region;
    public  EditText user_password;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        user_email = (EditText) findViewById(R.id.RegEmail);
        user_name = (EditText) findViewById(R.id.regName);
        user_region = (EditText) findViewById(R.id.RegRegion);
        user_password = (EditText) findViewById(R.id.RegPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn = (Button) findViewById(R.id.click);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user_name.getText().toString().trim();
                String email = user_email.getText().toString().trim();
                String region = user_region.getText().toString().trim();
                String password = user_password.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(getApplicationContext(), "Enter name address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter the address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(region)){
                    Toast.makeText(getApplicationContext(), "Enter the region!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Enter more than 6 letters!", Toast.LENGTH_SHORT).show();
                    return;
                }
              progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       // Toast.makeText(registration.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(registration.this, Login.class));
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            Toast.makeText(registration.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(registration.this, Login.class));
                            finish();
                        }
                    }
                });


            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
