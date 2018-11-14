package com.example.dbproject;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

class GetSchedule extends AsyncTask<Void, Void, Void> {
    private Context context;
    private static final String TAG = GetSchedule.class.getSimpleName();


    public GetSchedule(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context, "Json Data is downloading", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://www.fantasyfootballnerd.com/service/schedule/json/pr8xb92kbh8m/";
        String jsonString = sh.makeServiceCall(url);
        GameDBHandler gameDBHandler = new GameDBHandler(context, null, null, 2);

        Log.e(TAG, "Response from URL: " + jsonString);

        if (jsonString != null){
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArraySchedule = jsonObject.getJSONArray("Schedule");

                //TODO parse the data in JSON
                for (int i = 0; i< jsonArraySchedule.length(); i++){
                    JSONObject jsonObjectGame = jsonArraySchedule.getJSONObject(i);
                    Game game = null;
                    Date gameDate = null;
                    try
                    {
                        gameDate = new SimpleDateFormat("yyyy-MM-dd").parse(jsonObjectGame.getString("gameDate"));
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    game = new Game(i+10, jsonObjectGame.getString("homeTeam"), jsonObjectGame.getString("awayTeam"), gameDate);
                    int gameWeek = jsonObjectGame.getInt("gameWeek");

                    //game.setGameID(i+10);
                    //game.setHomeTeam(jsonObjectGame.getString("homeTeam"));
                    //game.setAwayTeam(jsonObjectGame.getString("awayTeam"));
                    //TODO convert date string to a date
                    //game.setGameDate(jsonObjectGame.getString("gameDate"));
                    game.setGameWeek(Integer.parseInt(jsonObjectGame.getString("gameWeek")));
                    game.setWinner(jsonObjectGame.getString("winner"));

                    gameDBHandler.addGame_Handler(game);

                }


            }catch (final JSONException e){
                Log.e(TAG, "Json parsing error: " + e.getMessage());


            }
        }else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        //Need to create an adapter and then return to Main Activity to display the list of games.
        //Something like this code
      //  MainActivity.testMethod("test");
    }

}
