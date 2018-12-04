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

    @Query("Delete from game")
    void clearGameTable();

    @Query("Update game set winner=:winner where game.gameID=:gameID")
    void updateWinner(int gameID, String winner);

    @Query("Select winner from game where game.gameID=:gameID")
    String getGameWinner(int gameID);

    //TODO not sure I like how I did this, gets the current game week all the way until the thursday game so if this is run
    //on a tuesday or wednesday the current week would be the one that just ended
    //so when filling in all weeks up to now in the random picking, it will do all the way through the current week
    @Query("Select gameWeek from Game where gameDate <:gameDate order by gameDate desc limit 1")
            int getGameWeekFromDate(String gameDate);


}
