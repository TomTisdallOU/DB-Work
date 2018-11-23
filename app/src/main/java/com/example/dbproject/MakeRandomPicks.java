package com.example.dbproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

public class MakeRandomPicks extends AsyncTask {
    private Context context = null;
    private int week = 0;
    private static final String TAG = MakeRandomPicks.class.getSimpleName();
    private GamePickerDatabase gamePickerDatabase;

    public MakeRandomPicks(Context context, int week) {
        this.context = context;
        this.week = week;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Adding Week " + week + " picks.", Toast.LENGTH_LONG).show();

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        gamePickerDatabase = GamePickerDatabase.getInstance(context);

        List<User> users = gamePickerDatabase.getUserDao().getAllUsers();
        List<Game> games = gamePickerDatabase.getGameDao().findGamesForWeek(week);
        Pick pick = new Pick();

        for (User user: users){
            for (Game game: games){

                pick.setGameID(game.getGameID());
                pick.setUserID(user.userID);
            //    pick.setPicksID(0);
                pick.setConfidence(0);
                if (Math.random() < 0.5)
                    pick.setTeamPicked(game.getHomeTeam());
                else
                    pick.setTeamPicked(game.getAwayTeam());

                //TODO check for existing pick before trying to insert.
                gamePickerDatabase.getPickDao().insert(pick);

            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        Toast.makeText(context, "Added Week " + week + " picks.", Toast.LENGTH_LONG).show();
    }
}
