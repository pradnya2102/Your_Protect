package com.example.yourprotect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener{

    EditText FullName, Email, Phone, Password, Age;
    Button RegisterBtn;
    TextView LoginBtn;
    RadioGroup radioGroup;
    String Gender;
    private FirebaseAuth.AuthStateListener fAuthListener;
    private FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        FullName = findViewById(R.id.FullName);
        Email = findViewById(R.id.Email);
        Phone = findViewById(R.id.Phone);
        Password = findViewById(R.id.Password);
        Age = findViewById(R.id.Age);
        radioGroup = findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        LoginBtn = findViewById(R.id.LoginBtn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    Password.setError("Password must be atleast 6 characters long.");
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }else {
                            Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.Female:
                Gender = "Female";
                break;
            case R.id.Male:
                Gender = "Male";
                break;
        }
    }
}