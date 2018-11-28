package com.example.dbproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PickDao {

    @Insert
    void insert(Pick pick);

    @Update
    void update (Pick pick);

    @Query("Select * from pick where pick.userID=:userID and pick.gameID =:gameID")
    Pick getPick(final int userID, final int gameID);

    @Delete
    void delete(Pick pick);

    @Query("Select * from pick")
    List<Pick> getAllPicks();

    @Query("Select * from pick where pick.userid=:userID")
    List<Pick> findGamesForWeek(final int userID);

    @Query("Delete from pick")
    void clearPickTable();


    @Query("Select user.userID as userId, user.userName, count(teamPicked) as totalPoints from pick left join game on pick.gameID = game.gameID and pick.teamPicked = game.winner " +
            "                                   left join user on user.userID = pick.userID  " +
            "                                   where gameWeek =:week group by  user.userName order by count(teamPicked) desc ")
    List<UserResults> getWeeklyResults(int week);

    @Query("Select user.userID as userId, user.userName, count(teamPicked) as totalPoints from pick left join game on pick.gameID = game.gameID and pick.teamPicked = game.winner " +
            "                                   left join user on user.userID = pick.userID  " +
            "                                   group by  user.userName order by count(teamPicked) desc ")
    List<UserResults> getOverallResults();

}
