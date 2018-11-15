package com.example.dbproject;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button addButton = null;
    Game game;
    int ID = 0;
    BottomNavigationView bottomNavigationView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GameDBHandler gameDBHandler = new GameDBHandler(MainActivity.this, null, null, 2);
                //++ID;
                //Game game = new Game();
                //game.setGameID(ID);
                //game.setHomeTeam("Detroit");
                //game.setAwayTeam("Pittsburgh");
                //Date date = new Date();
                //game.setGameDate(date);
                //dbHandler.addGame_Handler(game);

                new GetSchedule(MainActivity.this).execute();

                //Somehow need to get the GameDBHandler over here so I can display the game

            }
        });


        bottomNavigationView = findViewById(R.id.navigationView);
        if (bottomNavigationView != null){
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    return false;
                }
            });
        }

    }
}
