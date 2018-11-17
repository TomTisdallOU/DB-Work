package com.example.dbproject;

import java.util.Date;

public class Game {
    private int gameID;
    private String homeTeam;
    private String awayTeam;
    private String gameDate;
    private int gameWeek;
    private String winner;




    public Game(){
        this.gameID = 0;
        this.homeTeam = "";
        this.awayTeam = "";
        this.gameDate = null;
    }


    public Game(int gameID, String homeTeam, String awayTeam, String gameDate) {
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

    public String getGameDate() {
        return gameDate;
    }

    public String getWinner() { return winner; }

    public int getGameWeek() { return gameWeek; }

    public void setGameID(int gameID) { this.gameID = gameID; }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public void setWinner(String winner) { this.winner = winner; }

    public void setGameWeek(int gameWeek) { this.gameWeek = gameWeek; }

}
