package com.example.dbproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PickDao {

    @Insert
    void insert(Pick pick);

    @Update
    void update (Pick pick);


    @Delete
    void delete(Pick pick);

    @Query("Select * from pick")
    List<Pick> getAllPicks();

    @Query("Select * from pick where pick.userid=:userID")
    List<Pick> findGamesForWeek(final int userID);

    @Query("Delete from pick")
    void clearPickTable();


    @Query("Select user.userID, user.userName, count(*) as totalPoints from pick left join game on pick.gameID = game.gameID and pick.teamPicked = game.winner " +
            "                                   left join user on user.userID = pick.userID  " +
            "                                   where gameWeek =:week group by  user.userName")
    List<UserResults> getWeeklyResults(int week);
}
