package com.example.dbproject;

import android.os.Build;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.sql.Date;

public class AzurePicks {
    private int id;
    private DateTimeOffset createdAt;
    private DateTimeOffset updatedAt;
    private String version;
 //   private Boolean deleted;
    private String UserName;
    private int GameID;
    private String TeamPicked;

    public AzurePicks(String userName, int gameID) {
        UserName = userName;
        GameID = gameID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTimeOffset getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTimeOffset createdAt) {
        this.createdAt = createdAt;
    }

    public DateTimeOffset getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTimeOffset updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getGameID() {
        return GameID;
    }

    public void setGameID(int gameID) {
        GameID = gameID;
    }

    public String getTeamPicked() {
        return TeamPicked;
    }

    public void setTeamPicked(String teamPicked) {
        TeamPicked = teamPicked;
    }
}
