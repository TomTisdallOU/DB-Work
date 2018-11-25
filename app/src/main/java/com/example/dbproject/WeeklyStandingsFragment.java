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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeeklyStandingsFragment extends Fragment {
    GamePickerDatabase gamePickerDatabase = null;
    Spinner weekSpinner = null;
    TextView personNameTextView, pointsTextView = null;
    LinearLayout standingsLinearLayoutContainer = null;
    LinearLayout headerLinearLayoutContainer = null;
    ArrayList<String> listOfWeeks = new ArrayList<>();;


    public WeeklyStandingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.weekly_standings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        standingsLinearLayoutContainer =  view.findViewById(R.id.standingsLinearLayout);
        headerLinearLayoutContainer = view.findViewById(R.id.headerLinearLayout);
        weekSpinner = view.findViewById(R.id.weeksSpinner);
        personNameTextView = view.findViewById(R.id.personNameTextView);
        pointsTextView = view.findViewById(R.id.pointsTextView);

        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                standingsLinearLayoutContainer.removeAllViews();

                standingsLinearLayoutContainer.addView(weekSpinner);
                standingsLinearLayoutContainer.addView(headerLinearLayoutContainer);
                List<UserResults> standingsResults = gamePickerDatabase.getPickDao().getWeeklyResults(position + 1);

                PopulateStandings(standingsResults);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        weekSpinner.setEnabled(false);

        new LoadSpinner().execute();
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


    private class LoadSpinner extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            List<Integer> weekList = gamePickerDatabase.getGameDao().getListOfWeeks();
            //   ArrayList<String> listOfWeeks = new ArrayList<>();

            //TODO loop through integers of weeks and add "Week" to the front so the list says "Week 1, Week 2 etc.
            //TODO Figure out best way to "lock" weeks -- show old weeks as output only.  Maybe older by date?
            //TODO have the spinner default to the current week -- closest by date
            //TODO the URL data does return a current week, we can use that to determine what can be changed and what is output only.
            Iterator<Integer> itr = weekList.iterator();
            while (itr.hasNext()){
                listOfWeeks.add("Week " + itr.next());
            }

            return null;

            // return listOfWeeks;
        }

        @Override
        protected void onPostExecute(Void args){

            //TODO get R.id.Spinner and set the adapter to these results
            Spinner mySpinner = (Spinner) getView().findViewById(R.id.weeksSpinner);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_item, listOfWeeks);

            mySpinner.setAdapter(adapter);
            weekSpinner.setEnabled(true);

        }
    }

}
