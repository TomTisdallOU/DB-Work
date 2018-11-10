package com.example.dbproject;

import java.util.Date;

public class Game {
    private int gameID;
    private String homeTeam;
    private String awayTeam;
    private Date gameDate;


    public Game(int gameID, String homeTeam, String awayTeam, Date gameDate) {
        this.gameID = gameID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate = gameDate;
    }

    public int getGameID() {
        return gameID;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}
