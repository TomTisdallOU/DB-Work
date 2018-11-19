package com.example.dbproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GameDao {

    @Insert
    void insert(Game game);

    @Update
    void update (Game game);


    @Delete
    void delete(Game game);

    @Query("Select * from game")
    List<Game> getAllGames();

    @Query("Select * from game where game.gameWeek=:gameWeek")
    List<Game> findGamesForWeek(final int gameWeek);


    @Query("Select Distinct gameWeek from game")
    List<Integer> getListOfWeeks();


}
