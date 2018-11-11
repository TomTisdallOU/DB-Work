package com.example.dbproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText username, password = null;
    Button signInButton = null;
    TextView signUp = null;
    UserDBHandler userDatabase = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        username = findViewById(R.id.usernameEditText);
        password = findViewById(R.id.passwordEditText);
        signUp = findViewById(R.id.signUpTextView);
        signInButton = findViewById(R.id.signInButton);


        userDatabase = new UserDBHandler(this, null, null, 1);

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

                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();


                if(usernameValue.equals("") || passwordValue.equals(""))
                {
                    Toast.makeText(SignInActivity.this,"One of the values are not filled out. Please fill them out and try again.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(userDatabase.UserExistsInDatabase(usernameValue, passwordValue) != null)
                    {
                        //Intent intent = new Intent(SignInActivity.this, mainscreen.class);
                        // Put the user data into intent and pass to activity?
                        //startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SignInActivity.this,"Login failed. Invalid username and/or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}