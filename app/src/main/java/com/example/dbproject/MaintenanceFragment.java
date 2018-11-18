package com.example.dbproject;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MaintenanceFragment extends Fragment {

    Button addDataButton = null;
    //TODO Add a button to get week results -- just populating winners.

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
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



    }
}


