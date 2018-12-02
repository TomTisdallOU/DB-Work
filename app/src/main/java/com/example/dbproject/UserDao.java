package com.example.dbproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update (User user);


    @Delete
    void delete(User user);

    @Query("delete from user")
    void deleteAllUsers();

    @Query("Select * from user")
    List<User> getAllUsers();

    @Query("Select * from user where user.userID=:userID")
    User getUser(final int userID);

    @Query("Select * from user where user.userName=:userName and user.userPassword=:userPassword")
    User getUser(final String userName, String userPassword);


    @Query("Select gameWeek, count(teamPicked) as totalPoints from pick join game on pick.gameID = game.gameID and pick.teamPicked = game.winner " +
                " where pick.userId =:userID  group by gameWeek order by gameWeek ")
    List<UserSeasonResults> getUserSeasonResults(int userID);



}
