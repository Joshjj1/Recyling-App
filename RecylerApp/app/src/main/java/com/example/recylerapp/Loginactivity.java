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

public class Loginactivity extends AppCompatActivity {
    Button loginbtn;
    TextView tv3,tv4;
    EditText email,password;
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(Loginactivity.this,Homepage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        mAuth=FirebaseAuth.getInstance();
        loginbtn=findViewById(R.id.btn_login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email2,password2;
                email2=String.valueOf(email.getText());
                password2=String.valueOf(password.getText());

                if(TextUtils.isEmpty(email2)){
                    Toast.makeText(Loginactivity.this,"Enter email",Toast.LENGTH_LONG).show();
                    return;

                }
                if(TextUtils.isEmpty(password2)){
                    Toast.makeText(Loginactivity.this,"Enter password",Toast.LENGTH_LONG).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email2, password2)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                   // Log.d(TAG, "signInWithEmail:success");
                                   // FirebaseUser user = mAuth.getCurrentUser();
                                 //   updateUI(user);
                                    Toast.makeText(Loginactivity.this, "Login Successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Loginactivity.this,Homepage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                   // Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Loginactivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });

            }
        });

    }
}