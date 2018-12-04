package com.example.dbproject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoadWeekResults extends AsyncTask {
    private Context context;
    private static final String TAG = LoadWeekResults.class.getSimpleName();
    private GamePickerDatabase gamePickerDatabase;
    private int countOfGames = 0;
    private int week;

    public LoadWeekResults(Context context, int week) {
        this.context = context;
        this.week = week;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Preparing to update week " + week, Toast.LENGTH_LONG).show();
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        countOfGames = 0;

        HttpHandler sh = new HttpHandler();
        String url = "https://www.fantasyfootballnerd.com/service/schedule/json/pr8xb92kbh8m/";
        String jsonString = sh.makeServiceCall(url);
        gamePickerDatabase = GamePickerDatabase.getInstance(context);
        String winner = "";
        String gameWinner = "";

        Log.e(TAG, "Response from URL: " + jsonString);

        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArraySchedule = jsonObject.getJSONArray("Schedule");
                int length = jsonArraySchedule.length();
                //TODO parse the data in JSON
                for (int i = 0; i < jsonArraySchedule.length(); i++) {
                    JSONObject jsonObjectGame = jsonArraySchedule.getJSONObject(i);

                    if (jsonObjectGame.getInt("gameWeek") <= week) {

                        int gameID = jsonObjectGame.getInt("gameId");
                        gameWinner = jsonObjectGame.getString("winner");

                        winner = gamePickerDatabase.getGameDao().getGameWinner(gameID);
                        if (winner == null) {


                            gamePickerDatabase.getGameDao().updateWinner(gameID, gameWinner);
                            countOfGames++;
                        }
                    }

                    //TODO convert date string to a date???

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

        //TODO show count of games added
        Toast.makeText(context, "Updated week " + week + " with " + countOfGames + " games.", Toast.LENGTH_LONG).show();
    }

}



