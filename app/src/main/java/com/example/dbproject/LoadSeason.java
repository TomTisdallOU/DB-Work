package com.example.dbproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadSeason extends AsyncTask {
    private Context context;
    private static final String TAG = GetSchedule.class.getSimpleName();
    private GamePickerDatabase gamePickerDatabase;

    public LoadSeason(Context context) {
        this.context = context;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Json Data is downloading", Toast.LENGTH_LONG).show();
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        HttpHandler sh = new HttpHandler();
        String url = "https://www.fantasyfootballnerd.com/service/schedule/json/pr8xb92kbh8m/";
        String jsonString = sh.makeServiceCall(url);
        gamePickerDatabase = GamePickerDatabase.getInstance(context);

        //TODO -- Test this -- used to wipe out all games to reload them.
        gamePickerDatabase.getGameDao().clearGameTable();
        gamePickerDatabase.getPickDao().clearPickTable();
        Log.e(TAG, "Clearing games and picks successful");


        Log.e(TAG, "Response from URL: " + jsonString);

        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArraySchedule = jsonObject.getJSONArray("Schedule");
                int length = jsonArraySchedule.length();
                //TODO parse the data in JSON
                for (int i = 0; i < jsonArraySchedule.length(); i++) {
                    JSONObject jsonObjectGame = jsonArraySchedule.getJSONObject(i);
                    Game game = null;
                 //   game = new Game(i + 10, jsonObjectGame.getString("homeTeam"), jsonObjectGame.getString("awayTeam"), jsonObjectGame.getString("gameDate"));
                    int gameWeek = jsonObjectGame.getInt("gameWeek");

                    game = new Game(0,jsonObjectGame.getString("homeTeam"), jsonObjectGame.getString("awayTeam"),
                            jsonObjectGame.getString("gameDate"), Integer.parseInt(jsonObjectGame.getString("gameWeek")));

                    gamePickerDatabase.getGameDao().insert(game);

                    //game.setGameID(i+10);
                    //game.setHomeTeam(jsonObjectGame.getString("homeTeam"));
                    //game.setAwayTeam(jsonObjectGame.getString("awayTeam"));
                    //TODO convert date string to a date
                    //game.setGameDate(jsonObjectGame.getString("gameDate"));
                //    game.setGameWeek(Integer.parseInt(jsonObjectGame.getString("gameWeek")));
                  //  game.setWinner(jsonObjectGame.getString("winner"));

                    //gameDBHandler.addGame_Handler(game);

                }


            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());


            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }


    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        Toast.makeText(context, "Database load complete", Toast.LENGTH_LONG).show();
    }

}



