package com.example.dbproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class PicksDBHandler extends SQLiteOpenHelper {
    private static final int Database_Version = 2;
    private static final String Database_Name = "FootballPicker";
    private static final String Table_Picks = "Picks";

    private static final String Picks_Column_ID = "PicksID";
    private static final String Picks_Column_UserID = "UserID";
    private static final String Picks_Column_GameID = "GameID";
    private static final String Game_Column_PickedTeam = "PickedTeam";
    private static final String Game_Column_Confidence = "Confidence";


//TODO  1. create the Find, Add, Delete, Update, Getall methods    2. Clean up strings -- can I get Resource.String to work?  3. Move DB to DB Name

    public PicksDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name + ".db", factory, Database_Version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
