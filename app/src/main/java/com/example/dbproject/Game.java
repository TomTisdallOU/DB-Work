package com.example.dbproject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class Game {
    @PrimaryKey private int gameID;
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
        this.gameWeek = 0;
        this.winner = "";
    }


    public Game(int gameID, String homeTeam, String awayTeam, String gameDate, int gameWeek) {
        this.gameID = gameID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameDate = gameDate;
        this.gameWeek = gameWeek;
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
