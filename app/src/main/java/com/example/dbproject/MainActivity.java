package com.example.dbproject;

import android.os.Handler;
import android.os.Message;
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
    BottomNavigationView bottomNavigationView = null;
    Handler handler = null;
    GameDBHandler gameDBHandler = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                gameDBHandler = (GameDBHandler) msg.getData().getSerializable("GameDBHandler");
            }
        };

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

                new GetSchedule(MainActivity.this, handler).execute();

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
