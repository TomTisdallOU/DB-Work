package com.example.dbproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

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

                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String emailValue = email.getText().toString();

                if(!usernameValue.equals("") && !passwordValue.equals("") && !emailValue.equals(("")))
                {
                    if(userDB.UserExistsInDatabase(usernameValue, passwordValue) == null)
                    {

                        int id = new Random().nextInt();
                        if(id < 0)
                            id = id * -1;

                        User user = new User(id, usernameValue, passwordValue, emailValue, "");
                        userDB.addUser_Handler(user);
                        Toast.makeText(RegisterActivity.this,"Account successfully created.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, SignInActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this,"Account already exists with that username and password.", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Please fill out all values.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
