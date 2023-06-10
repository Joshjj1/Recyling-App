package com.example.recylerapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signupactivity extends AppCompatActivity {
   Button register;
   TextView tv3,tv4,tv5;
   EditText email,password;
   FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(Signupactivity.this,Homepage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupactivity);

        mAuth=FirebaseAuth.getInstance();
        register=findViewById(R.id.button);
        email=findViewById(R.id.email_id);
        password=findViewById(R.id.password_id);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1,password1;
                email1=String.valueOf(email.getText());
                password1=String.valueOf(password.getText());

                if(TextUtils.isEmpty(email1)){
                    Toast.makeText(Signupactivity.this,"Enter email",Toast.LENGTH_LONG).show();
                    return;

                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(Signupactivity.this,"Enter password",Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "createUserWithEmail:success");
                                  //  FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);
                                    Toast.makeText(Signupactivity.this, "Registered Succesfully",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Signupactivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });


            }
        });
    }
}