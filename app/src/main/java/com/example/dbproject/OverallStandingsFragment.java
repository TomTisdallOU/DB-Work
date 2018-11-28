package com.example.dbproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OverallStandingsFragment extends Fragment {
    GamePickerDatabase gamePickerDatabase = null;
    LinearLayout standingsLinearLayoutContainer = null;
    LinearLayout headerLinearLayoutContainer = null;
    ArrayList<String> listOfWeeks = new ArrayList<>();
    ArrayAdapter<UserResults> arrayAdapter = null;
    ListView overallStandingsView = null;


    public OverallStandingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.overall_standings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        standingsLinearLayoutContainer =  view.findViewById(R.id.standingsLinearLayout);
        headerLinearLayoutContainer = view.findViewById(R.id.headerLinearLayout);
        overallStandingsView = view.findViewById(R.id.overallStandings);



        List<UserResults> standingsResults = gamePickerDatabase.getPickDao().getOverallResults();
        arrayAdapter = new ArrayAdapter<UserResults>(getContext(), android.R.layout.simple_list_item_2, standingsResults);
        overallStandingsView.setAdapter(arrayAdapter);


    }



    void PopulateStandings(List<UserResults> userResults) {
        int i = 1;

        for (UserResults userResult : userResults) {
            // Render item layout
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.standings_result, null);
            //constraintLayout.setTag(game);


            TextView personNameTextView = constraintLayout.findViewById(R.id.personNameTextView);
            TextView pointsTextView = constraintLayout.findViewById(R.id.pointsTextView);

            personNameTextView.setText(String.valueOf(userResult.getUserName()));
            pointsTextView.setText(String.valueOf(userResult.getTotalPoints()));

            // Change background color
            //constraintLayout.setBackgroundColor(i % 2 == 0? Color.BLUE: Color.CYAN);

            // Add child in picks constraint layout
            standingsLinearLayoutContainer.addView(constraintLayout);

            i++;
        }
    }



}
