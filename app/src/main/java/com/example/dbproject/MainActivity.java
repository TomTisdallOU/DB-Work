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
                //GameDBHandler dbHandler = new GameDBHandler(v.getContext(), null, null, 1);
                //++ID;
                //Game game = new Game();
                //game.setGameID(ID);
                //game.setHomeTeam("Detroit");
                //game.setAwayTeam("Pittsburgh");
                //Date date = new Date();
                //game.setGameDate(date);
                //dbHandler.addGame_Handler(game);

                new GetSchedule(MainActivity.this).execute();

                int x = 3;
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
