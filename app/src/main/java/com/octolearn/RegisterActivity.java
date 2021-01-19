package com.octolearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText mEmail, mPassword, mConfirmPassword, mNickname;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mNickname   = findViewById(R.id.nickname);
        mEmail      = findViewById(R.id.email);
        mPassword   = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirmPassword);

        fAuth = FirebaseAuth.getInstance();

        mRegisterBtn = (Button) findViewById(R.id.registerButton);
        mLoginBtn = (TextView) findViewById(R.id.loginButton);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = mNickname.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mConfirmPassword.getText().toString().trim();
                if(!validateRegistrationInput(nickname, email, password, confirmPassword)) {

                    if (TextUtils.isEmpty(email)) {
                        mEmail.setError("Email is required");
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        mPassword.setError("Password is required");
                    }

                    if (password.length() < 6) {
                        mPassword.setError("Password is to short (min 6 characters");
                    }

                    if (TextUtils.isEmpty(confirmPassword)) {
                        mPassword.setError("Confirm your password");
                    }
                }
                if(fAuth.getCurrentUser() != null){
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });



    }
    public static Boolean validateRegistrationInput(String nickname, String email, String password, String confirmPassword){
        if(nickname.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return false;
        }
        if(password != confirmPassword) {
            return false;
        }
        if(!password.matches(".*\\d.*")){
            return false;
        }
        if(!email.contains("@")){
            return false;
        }
        if(password.length()<3){
            return false;
        }
        return true;
    }
}