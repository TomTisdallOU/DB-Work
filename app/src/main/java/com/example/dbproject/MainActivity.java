package com.example.dbproject;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView = null;
    User user = null;
    GamePickerDatabase gamePickerDatabase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get User
        gamePickerDatabase = GamePickerDatabase.getInstance(this);
        Bundle extraData = getIntent().getExtras();
        if (extraData != null){
            int userID = extraData.getInt("UserID");
            user = gamePickerDatabase.getUserDao().getUser(userID);

        }


        bottomNavigationView = findViewById(R.id.navigationView);
        if (bottomNavigationView != null){
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(2));

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    selectFragment(menuItem);
                    return false;
                }

            });
        }

    }

    public int getUserID(){
        return user.userID;
    }


    //TODO get user information in the fragments.  how to do that?
    protected void selectFragment(MenuItem item){
        item.setChecked(true);

        switch (item.getItemId()){
            case R.id.navigation_person:
                pushFragment(new UserFragment());
                break;
            case R.id.navigation_standings:
                pushFragment(new StandingsFragment());
                break;
            case R.id.navigation_settings:
                pushFragment(new MaintenanceFragment());
                break;
        }
    }

    protected void pushFragment(Fragment fragment){
        if (fragment==null)
            return;



        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragmentTransaction != null){
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.commit();
            }
        }
    }



}
