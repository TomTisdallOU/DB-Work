package com.example.dbproject;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pick {
    @PrimaryKey(autoGenerate = true) private int picksID;
    private int userID;
    private int gameID;
    private String teamPicked;
    private int confidence;



    public Pick(){
        //picksID = -1;
        userID = -1;
        gameID = -1;
        teamPicked = "";
        confidence = 0;
    }

    public Pick(int userID, int gameID, String teamPicked, int confidence) {
        this.userID = userID;
        this.gameID = gameID;
        this.teamPicked = teamPicked;
        this.confidence = confidence;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getTeamPicked() {
        return teamPicked;
    }

    public void setTeamPicked(String teamPicked) {
        this.teamPicked = teamPicked;
    }

    public int getPicksID() {
        return picksID;
    }

    public void setPicksID(int picksID) {
        this.gameID = picksID;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }
}
