package com.example.dbproject;

import android.content.DialogInterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MaintenanceFragment extends Fragment {

    Button addDataButton = null;
    Button getWeeklyResults = null;
    GamePickerDatabase gamePickerDatabase = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.maintenance_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        addDataButton = view.findViewById(R.id.buttonAddData);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadSeason(getActivity()).execute();
            }
        });

        getWeeklyResults = view.findViewById(R.id.buttonGetWeekResults);
        getWeeklyResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inviteDialog();
            }
        });

    }


    public void inviteDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select the week to update");

        final Spinner weekSpinner = new Spinner(getActivity());
        ArrayList<String> listOfWeeks = new ArrayList<>();
        List<Integer> weekList = gamePickerDatabase.getGameDao().getListOfWeeks();
        Iterator<Integer> itr = weekList.iterator();
        while (itr.hasNext()){
            listOfWeeks.add("Week " + itr.next());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_item, listOfWeeks);

        weekSpinner.setAdapter(adapter);
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
}


