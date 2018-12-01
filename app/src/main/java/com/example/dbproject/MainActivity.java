package com.example.dbproject;


import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements UserSettings.OnFragmentInteractionListener {


    BottomNavigationView bottomNavigationView = null;
    User user = null;
    GamePickerDatabase gamePickerDatabase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AzureServiceAdapter.Initialize(this);
      //  mclient = AzureServiceAdapter.getInstance().getClient();



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

        Bundle arguments = new Bundle();
        arguments.putInt("UserID", getUserID());
        fragment.setArguments(arguments);



        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (fragmentTransaction != null){
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
