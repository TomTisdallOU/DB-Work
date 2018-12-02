package com.example.dbproject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        MakeRandomWeekPicks makeRandomWeekPicks = new MakeRandomWeekPicks(context);

        if (week == 0){
            gamePickerDatabase = GamePickerDatabase.getInstance(context);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(Calendar.getInstance().getTime());
            int currentWeek = gamePickerDatabase.getGameDao().getGameWeekFromDate(date);
            for (int i = 1; i <= currentWeek;i++) {
                makeRandomWeekPicks.MakeWeekPicks(i);

            }

        }else {
            makeRandomWeekPicks.MakeWeekPicks(week);
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        Toast.makeText(context, "Added Week " + week + " picks.", Toast.LENGTH_LONG).show();
    }
}
