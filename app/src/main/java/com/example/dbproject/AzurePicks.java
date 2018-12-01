package com.example.dbproject;

import android.os.Build;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

import java.sql.Date;

public class AzurePicks {
    private String id;
  //  private DateTimeOffset createdAt;
  //  private DateTimeOffset updatedAt;
    private String version;
 //   private Boolean deleted;
    private String UserName;
    private String GameID;
    private String TeamPicked;

    public AzurePicks(String userName, String gameID, String teamPicked) {
        id = "";
        UserName = userName;
        GameID = gameID;
        TeamPicked = teamPicked;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public DateTimeOffset getCreatedAt() {
 //       return createdAt;
 //   }

 //   public void setCreatedAt(DateTimeOffset createdAt) {
 //       this.createdAt = createdAt;
 //   }

//    public DateTimeOffset getUpdatedAt() {
//        return updatedAt;
 //   }

//    public void setUpdatedAt(DateTimeOffset updatedAt) {
//        this.updatedAt = updatedAt;
//    }

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

    public String getGameID() {
        return GameID;
    }

    public void setGameID(String gameID) {
        GameID = gameID;
    }

    public String getTeamPicked() {
        return TeamPicked;
    }

    public void setTeamPicked(String teamPicked) {
        TeamPicked = teamPicked;
    }
}
