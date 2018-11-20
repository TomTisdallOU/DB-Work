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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserPicksFragment extends Fragment {
    GamePickerDatabase gamePickerDatabase = null;
    Button savePicksButton = null;

    GameDBHandler gameDBHandler = null;
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

        weekSpinner = view.findViewById(R.id.weekSpinner);
        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //TODO Cheated the picker #, assumed position + 1 shows the weeks.  Probably ok for us to leave.
                //TODO Navigation menu is overlapping the last game of the week

                picksLinearLayoutContainer.removeAllViews();

                picksLinearLayoutContainer.addView(savePicksButton);
                List<Game> games = gamePickerDatabase.getGameDao().findGamesForWeek(position + 1);

                PopulateActivityWithGames(games);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        weekSpinner.setEnabled(false);

    //    handler = new Handler(){

     //       @Override
     //       public void handleMessage(Message msg)
     //       {
     //           super.handleMessage(msg);
     //           gameDBHandler = (GameDBHandler) msg.getData().getSerializable("GameDBHandler");
//
  //              PopulateSpinnner();
    //        }
    //    };

        //    new GetSchedule(getActivity(), handler).execute();
        new LoadSpinner2().execute();

    }

    /*
    private void PopulateSpinnner()
    {
        ArrayList<String> weeksPlayedInSeasonList = new ArrayList<>();
        int weeks = gameDBHandler.WeeksPlayedInSeason();
        for(int i=1; i <= weeks; i++)
            weeksPlayedInSeasonList.add("Week " + String.valueOf(i));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, weeksPlayedInSeasonList);
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
*/


    void PopulateActivityWithGames(List<Game> gamesForWeekList)
    {
        int i = 0;

        for (Game game : gamesForWeekList)
        {
            // Render item layout
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.game, null);

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

