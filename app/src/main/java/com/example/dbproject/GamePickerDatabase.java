package com.example.dbproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class, Pick.class, Game.class}, version=1)
public  abstract class GamePickerDatabase extends RoomDatabase {

    private static final String DB_Name ="FootballPicker2.db";
    private static GamePickerDatabase gamePickerDB;



    public abstract UserDao getUserDao();
    public abstract GameDao getGameDao();
    public abstract PickDao getPickDao();

    public static GamePickerDatabase getInstance(Context context){
        if (gamePickerDB == null){
            gamePickerDB = buildDatabaseInstance(context);
        }
        return gamePickerDB;
    }

    private static GamePickerDatabase buildDatabaseInstance(Context context){
        return Room.databaseBuilder(context, GamePickerDatabase.class, DB_Name).allowMainThreadQueries().build();
    }

    public void cleanUp(){
        gamePickerDB = null;
    }

}
