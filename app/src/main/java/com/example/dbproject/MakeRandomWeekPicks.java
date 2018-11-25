package com.example.dbproject;

import android.content.Context;

import java.util.List;

public class MakeRandomWeekPicks {
    private Context context = null;
    private int week = 0;
    private GamePickerDatabase gamePickerDatabase;


    public MakeRandomWeekPicks(Context context) {
        this.context = context;

    }

    public void MakeWeekPicks(int week) {
        gamePickerDatabase = GamePickerDatabase.getInstance(context);

        List<User> users = gamePickerDatabase.getUserDao().getAllUsers();
        List<Game> games = gamePickerDatabase.getGameDao().findGamesForWeek(week);
        Pick pick = new Pick();

        for (User user : users) {
            for (Game game : games) {

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

    }
}