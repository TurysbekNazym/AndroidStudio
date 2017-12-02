package com.example.admin.menagmenttool;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {


    public EditText email;
    public EditText password;
    public TextView btn;
    public TextView btn1;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private String sendig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.userEmail);
        password = (EditText) findViewById(R.id.userPassword);
        btn = (TextView) findViewById(R.id.login);
        btn1 = (TextView) findViewById(R.id.welcome);

      btn1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Login.this, registration.class);
              startActivity(intent);
          }
      });
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String user_email = email.getText().toString();
              sendig = user_email;
              final String user_password = password.getText().toString();

              if (TextUtils.isEmpty(user_email)) {
                  Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                  return;
              }

              if (TextUtils.isEmpty(user_password)) {
                  Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                  return;
              }

//              progressBar.setVisibility(View.VISIBLE);

              auth.signInWithEmailAndPassword(user_email, user_password)
                      .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              // If sign in fails, display a message to the user. If sign in succeeds
                              // the auth state listener will be notified and logic to handle the
                              // signed in user can be handled in the listener.
//                              progressBar.setVisibility(View.GONE);
                              if (!task.isSuccessful()) {
                                  // there was an error
                                  if (user_password.length() < 6) {
                                      password.setError(("more than 5"));
                                  } else {
                                      Toast.makeText(Login.this, "No connection", Toast.LENGTH_LONG).show();
                                  }
                              } else {

                                  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                  String uid = user.getUid();
                                  Intent intent = new Intent(Login.this, TabActivity.class);
                                  intent.putExtra("Message",uid);
                                  startActivity(intent);
                                  finish();
                              }
                          }
                      });
          }
      });
    }


}
