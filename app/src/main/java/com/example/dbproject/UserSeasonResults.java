package com.example.dbproject;

public class UserSeasonResults {
    private int gameWeek;
    private int totalPoints;

    public UserSeasonResults() {
        this.gameWeek = 0;
        this.totalPoints = 0;
    }

    public int getGameWeek() {
        return gameWeek;
    }

    public void setGameWeek(int gameWeek) {
        this.gameWeek = gameWeek;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
