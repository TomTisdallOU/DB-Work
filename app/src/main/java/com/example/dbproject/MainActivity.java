package com.example.dbproject;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addButton = null;
    BottomNavigationView bottomNavigationView = null;
    Handler handler = null;
    GameDBHandler gameDBHandler = null;
    Spinner weekSpinner = null;
    RelativeLayout relativeLayoutContainer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayoutContainer = findViewById(R.id.activity_main);

        weekSpinner = findViewById(R.id.weekSpinner);
        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<Game> games = gameDBHandler.GetGamesForWeek(position + 1);
                PopulateActivtyWithGames(games);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                gameDBHandler = (GameDBHandler) msg.getData().getSerializable("GameDBHandler");

                PopulateSpinnner();
            }
        };

        new GetSchedule(MainActivity.this, handler).execute();


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


    private void PopulateSpinnner()
    {
        ArrayList<String> weeksPlayedInSeasonList = new ArrayList<>();
        int weeks = gameDBHandler.WeeksPlayedInSeason();
        for(int i=1; i <= weeks; i++)
            weeksPlayedInSeasonList.add("Week " + String.valueOf(i));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, weeksPlayedInSeasonList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(spinnerAdapter);
        weekSpinner.setSelection(spinnerAdapter.getPosition("Week 11"));

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(weekSpinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(500);
            popupWindow.setWidth(200);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
    }



    void PopulateActivtyWithGames(ArrayList<Game> gamesForWeekList)
    {
        for (Game game : gamesForWeekList)
        {
            // Render item layout
            LayoutInflater inflater = LayoutInflater.from(this);
            ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.game, null);

            TextView awayTeamTextView = constraintLayout.findViewById(R.id.awayTeamTextView);
            TextView homeTeamTextView = constraintLayout.findViewById(R.id.homeTeamTextView);
            TextView dateTextView = constraintLayout.findViewById(R.id.dateTextView);

            Button awayTeamButton = constraintLayout.findViewById(R.id.awayTeamButton);
            Button homeTeamButton = constraintLayout.findViewById(R.id.homeTeamButton);


            awayTeamTextView.setText(game.getAwayTeam());
            homeTeamTextView.setText(game.getHomeTeam());
            dateTextView.setText(game.getGameDate());

            awayTeamButton.setText(game.getAwayTeam());
            homeTeamButton.setText(game.getHomeTeam());

            // Add child in Linear layout
            relativeLayoutContainer.addView(constraintLayout);
        }
    }
}
