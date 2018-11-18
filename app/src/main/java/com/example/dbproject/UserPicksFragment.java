package com.example.dbproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class UserPicksFragment extends Fragment {
    GamePickerDatabase gamePickerDatabase = null;
    Button savePicksButton = null;
    Handler handler = null;
    GameDBHandler gameDBHandler = null;
    Spinner weekSpinner = null;
    LinearLayout picksLinearLayoutContainer = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        gamePickerDatabase = GamePickerDatabase.getInstance(getActivity());
        
        

        picksLinearLayoutContainer =  container.findViewById(R.id.gamesLinearLayout);
        savePicksButton = container.findViewById(R.id.savePicksButton);

        weekSpinner = container.findViewById(R.id.weekSpinner);
        weekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<Game> games = gameDBHandler.GetGamesForWeek(position + 1);
                PopulateActivityWithGames(games);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                gameDBHandler = (GameDBHandler) msg.getData().getSerializable("GameDBHandler");

                PopulateSpinnner();
            }
        };

        new GetSchedule(getActivity(), handler).execute();



        return inflater.inflate(R.layout.user_picks_fragment, container, false);
    }

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



    void PopulateActivityWithGames(ArrayList<Game> gamesForWeekList)
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

            Button awayTeamButton = constraintLayout.findViewById(R.id.awayTeamButton);
            Button homeTeamButton = constraintLayout.findViewById(R.id.homeTeamButton);


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
}
