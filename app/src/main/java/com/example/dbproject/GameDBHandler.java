package com.example.dbproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class GameDBHandler extends SQLiteOpenHelper {
    //DB Info
    private static final int Database_Version = 1;
    private static final String Database_Name =  Resources.getSystem().getString(R.string.DB_Name);
    private static final String Table_Game = Resources.getSystem().getString(R.string.Game_Table_Name);
    private static final String Game_Column_ID = "GameID";
    private static final String Game_Column_HomeTeam = "HomeTeam";
    private static final String Game_Column_AwayTeam = "AwayTeam";
    private static final String Game_Column_GameDate = "GameDate";
    private static final String Game_Column_GameWeek = "Week";
    private static final String Game_Column_GameWinner = "Winner";



    public GameDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name, factory, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //TODO maybe remove this at some point - used to load table for simplicity
        db.execSQL("Drop Table IF Exists Game");

        String Create_Game_Table = "Create table " + Table_Game + "(" + Game_Column_ID + " Integer Primary Key ," +
                Game_Column_HomeTeam + " TEXT, " + Game_Column_AwayTeam + " TEXT, " + Game_Column_GameDate + "TEXT" +
                Game_Column_GameWeek + "Integer" + Game_Column_GameWinner + " TEXT)";
        db.execSQL(Create_Game_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String loadGameHandler(){
        String result = "";
        String query = "Select * From" + Table_Game;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            int gameID = cursor.getInt(0);
            String homeTeam = cursor.getString(1);
            String awayTeam = cursor.getString(2);
            String gameDate = cursor.getString(3);
            int gameWeek = cursor.getInt(4);
            String gameWinner = cursor.getString(5);
            result = gameID + " " + homeTeam + " " + awayTeam + " " + gameDate + " " + gameWeek +
                    " " + gameWinner + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addGame_Handler(Game game){
        ContentValues values = new ContentValues();
        values.put(Game_Column_ID, game.getGameID());
        values.put(Game_Column_HomeTeam, game.getHomeTeam());
        values.put(Game_Column_AwayTeam, game.getAwayTeam());
        values.put(Game_Column_GameDate, game.getGameDate().toString());
        values.put(Game_Column_GameWeek, game.getGameWeek());
        values.put(Game_Column_GameWinner, game.getWinner());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Table_Game, null, values);
        db.close();

    }
    public Game findGameHander(int gameID){
        String query = "Select * From " + Table_Game + " Where " + Game_Column_ID + " = " + gameID;
        SQLiteDatabase db = getWritableDatabase();
        Date date = null;
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            game.setGameID(cursor.getInt(0));
            game.setHomeTeam(cursor.getString(1));
            game.setAwayTeam(cursor.getString(2));
            // Added try/catch to get build to work
            try
            {
                date = new SimpleDateFormat("dd/MM/YYYY").parse(cursor.getString(3));

            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            game.setGameID(cursor.getInt(4));
            game.setWinner(cursor.getString(5));

            game.setGameDate(date);
            cursor.close();
        } else{
            game = null;
        }
        db.close();
        return game;
    }

    public boolean deleteGameHandler(int gameID){
        boolean result = false;
        String query = "Select * From " + Table_Game + " Where " + Game_Column_ID + " = "  + gameID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            game.setGameID(cursor.getInt(0));
            // Commented out to get build to work
            //db.delete(Table_Game, )
        }

        // Added to get build to work
        return true;

    }
    public boolean updateGameHandler(int gameID, String homeTeam){

        // Added to get build to work
        return true;
    }





}
