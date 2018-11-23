package com.example.dbproject;

public class UserResults {
    private String userName;
    private int totalPoints;


    public UserResults() {
        this.userName = "";
        this.totalPoints = 0;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}