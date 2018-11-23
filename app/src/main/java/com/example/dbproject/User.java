package com.example.dbproject;



//TODO - create add, update, delete, get classes
//TODO - when creating or updating be sure to make sure the userName is unique.

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    //TODO Make these guys private
    @PrimaryKey(autoGenerate = true) int userID;
    String userName;
    String userPassword;
    String userEmail;
    String userPhoneNumber;

    public User(){
        userID = 0;
        userName = "";
        userPassword = "";
        userEmail = "";
        userPhoneNumber = "";
    }


    public User(int userID, String userName, String userPassword, String userEmail, String userPhoneNumber) {

        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
