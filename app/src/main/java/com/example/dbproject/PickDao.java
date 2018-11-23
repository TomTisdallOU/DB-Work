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
}
