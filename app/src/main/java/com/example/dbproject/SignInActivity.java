package com.example.dbproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    EditText username, password = null;
    Button signInButton = null;
    TextView signUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        signUp = findViewById(R.id.signUpTextView);
        signInButton = findViewById(R.id.signInButton);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the typed in username & password
                // Check to see if the user exist in the database
                // If they do, launch them to screen where you can make picks
                // If they dont, make a toast that says login failed (invalid username/password)
            }
        });

    }
}
