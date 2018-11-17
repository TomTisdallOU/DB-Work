package com.example.dbproject;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton = null;
    BottomNavigationView bottomNavigationView = null;
    Handler handler = null;
    GameDBHandler gameDBHandler = null;
    Spinner weekSpinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weekSpinner = findViewById(R.id.weekSpinner);

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
}
