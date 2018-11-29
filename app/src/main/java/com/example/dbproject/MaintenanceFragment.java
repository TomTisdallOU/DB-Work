package com.example.dbproject;

import android.content.DialogInterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class MaintenanceFragment extends Fragment {

    Button btnAddDataButton = null;
    Button btnGetWeeklyResults = null;
    Button btnAddRandomUsers = null;
    Button btnMakeRandomPicks = null;
    Button btnMakeAllRandomPicks = null;
    GamePickerDatabase gamePickerDatabase = null;
    MobileServiceClient mclient = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        AzureServiceAdapter.Initialize(getActivity());
        mclient = AzureServiceAdapter.getInstance().getClient();



        return inflater.inflate(R.layout.maintenance_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        btnAddDataButton = view.findViewById(R.id.buttonAddData);
        btnAddDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadSeason(getActivity()).execute();
            }
        });

        btnGetWeeklyResults = view.findViewById(R.id.buttonGetWeekResults);
        btnGetWeeklyResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResultsforWeekDialog();
            }
        });

        btnAddRandomUsers = view.findViewById(R.id.buttonAddRandomUsers);
        btnAddRandomUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getHowManyUserstoAddDialog();
            }
        });


        btnMakeRandomPicks = view.findViewById((R.id.buttonMakeRandomPicks));
        btnMakeRandomPicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeRandomPicksforWeek();
            }
        });

        btnMakeAllRandomPicks = view.findViewById(R.id.buttonMakeAllRandomPicks);
        btnMakeAllRandomPicks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeRandomPicks(getActivity(),0).execute();
            }
        });


    }

    private void getHowManyUserstoAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter how many users to add");

        final EditText usersToAdd = new EditText(getActivity());
        usersToAdd.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(usersToAdd);


// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO load results for the specific week
                //Adding one due to index starting at 0 -- so pick 5 means week 6
                int numberOfUserstoAdd =  Integer.parseInt(usersToAdd.getText().toString());
                new AddUsers(getActivity(),numberOfUserstoAdd).execute();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }



    public void getResultsforWeekDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select the week to update");

        final Spinner weekSpinner = new Spinner(getActivity());
        weekSpinner.setAdapter(getWeeksList());
        weekSpinner.setEnabled(true);
        builder.setView(weekSpinner);


// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               //TODO load results for the specific week
                //Adding one due to index starting at 0 -- so pick 5 means week 6
                int selectedWeek = weekSpinner.getSelectedItemPosition() + 1;
                new LoadWeekResults(getActivity(),selectedWeek).execute();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void makeRandomPicksforWeek(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select the week to update");

        final Spinner weekSpinner = new Spinner(getActivity());
        weekSpinner.setAdapter(getWeeksList());
        weekSpinner.setEnabled(true);
        builder.setView(weekSpinner);


// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO load results for the specific week
                //Adding one due to index starting at 0 -- so pick 5 means week 6
                int selectedWeek = weekSpinner.getSelectedItemPosition() + 1;
                //TOD MAke the random picks
                new MakeRandomPicks(getActivity(),selectedWeek).execute();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public ArrayAdapter<String> getWeeksList(){
        ArrayList<String> listOfWeeks = new ArrayList<>();
        List<Integer> weekList = gamePickerDatabase.getGameDao().getListOfWeeks();
        Iterator<Integer> itr = weekList.iterator();
        while (itr.hasNext()){
            listOfWeeks.add("Week " + itr.next());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_item, listOfWeeks);


        return adapter;
    }
}


