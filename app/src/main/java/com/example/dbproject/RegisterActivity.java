package com.example.dbproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email = null;
    Button createAccountButton = null;
    UserDBHandler userDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        createAccountButton = findViewById(R.id.createAccountButton);


        userDB = new UserDBHandler(this, null, null, 1);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check to see if account already exists
                // If it doesn't add the user to the database
                // If it does, Toast saying user already exists
            }
        });
    }
}