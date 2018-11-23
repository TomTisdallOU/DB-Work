package com.example.dbproject;

import android.os.AsyncTask;
import android.os.Bundle;
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

//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserPicksFragment extends Fragment {
    GamePickerDatabase gamePickerDatabase = null;
    Button savePicksButton = null;
    int userID = 0;

//    GameDBHandler gameDBHandler = null;
    Spinner weekSpinner = null;
    LinearLayout picksLinearLayoutContainer = null;

    ArrayList<String> listOfWeeks = new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        return inflater.inflate(R.layout.user_picks_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        picksLinearLayoutContainer =  view.findViewById(R.id.gamesLinearLayout);
        savePicksButton = view.findViewById(R.id.savePicksButton);

        //Get UserID
        MainActivity mainActivity = (MainActivity) getActivity();
        userID = mainActivity.getUserID();


        weekSpinner = view.findViewById(R.id.weekSpinner);
        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                picksLinearLayoutContainer.removeAllViews();

                picksLinearLayoutContainer.addView(weekSpinner);
                picksLinearLayoutContainer.addView(savePicksButton);
                //TODO Cheated the picker #, assumed position + 1 shows the weeks.  Probably ok for us to leave.
                List<Game> games = gamePickerDatabase.getGameDao().findGamesForWeek(position + 1);

                PopulateActivityWithGames(games);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        weekSpinner.setEnabled(false);

        new LoadSpinner2().execute();

    }




    void PopulateActivityWithGames(List<Game> gamesForWeekList)
    {
        int i = 1;

        for (Game game : gamesForWeekList)
        {
            // Render item layout
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.game, null);
            constraintLayout.setTag(game);


            TextView awayTeamTextView = constraintLayout.findViewById(R.id.awayTeamTextView);
            TextView homeTeamTextView = constraintLayout.findViewById(R.id.homeTeamTextView);
            TextView dateTextView = constraintLayout.findViewById(R.id.dateTextView);

            final Button awayTeamButton = constraintLayout.findViewById(R.id.awayTeamButton);
            final Button homeTeamButton = constraintLayout.findViewById(R.id.homeTeamButton);

            awayTeamButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(true);
                    homeTeamButton.setSelected(false);

                }
            });

            homeTeamButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(true);
                    awayTeamButton.setSelected(false);
                }
            });


            awayTeamTextView.setText(game.getAwayTeam());
            homeTeamTextView.setText(game.getHomeTeam());
            dateTextView.setText(game.getGameDate());

            awayTeamButton.setText(game.getAwayTeam());
            homeTeamButton.setText(game.getHomeTeam());

            // Change background color
            //constraintLayout.setBackgroundColor(i % 2 == 0? Color.BLUE: Color.CYAN);

            // Add child in picks constraint layout
            picksLinearLayoutContainer.addView(constraintLayout);

            i++;
        }

        savePicksButton.setVisibility(View.VISIBLE);
        savePicksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
                Button awayButton = null;
                Game game = null;
                final int count = picksLinearLayoutContainer.getChildCount();
                for (int i = 0; i< count; i++) {
                    final View child = picksLinearLayoutContainer.getChildAt(i);

                    if (child instanceof ConstraintLayout){
                        game = (Game) child.getTag();
                        awayButton = child.findViewById(R.id.awayTeamButton);
                        Pick pick;
                        if (awayButton.isSelected()){
                            //TODO Not the greatest logic, will always pick home team if no team is selected.
                            //TODO this currently does an insert, should see if one exists and update instead
                            pick = new Pick(0, 1, game.getGameID(), game.getAwayTeam(), 1);

                        } else {
                            pick = new Pick(0, 1, game.getGameID(), game.getHomeTeam(), 1);
                        }
                        gamePickerDatabase.getPickDao().insert(pick);
                    }
                }
            }
        });



    }

private class LoadSpinner2 extends AsyncTask<Void, Void, Void> {


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
        Spinner mySpinner = (Spinner) getView().findViewById(R.id.weekSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(), android.R.layout.simple_spinner_item, listOfWeeks);

        mySpinner.setAdapter(adapter);
        weekSpinner.setEnabled(true);
        
    }
}



}
