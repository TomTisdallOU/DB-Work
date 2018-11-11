package com.example.dbproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class UserDBHandler extends SQLiteOpenHelper {
    //DB Info
    private static final int Database_Version = 1;
    private static final String Database_Name =  Resources.getSystem().getString(R.string.DB_Name);
    private static final String Table_User = Resources.getSystem().getString(R.string.User_Table_Name);
    private static final String User_Column_ID = "GameID";
    private static final String User_Column_UserName = "UserName";
    private static final String User_Column_Password = "UserPassword";
    private static final String User_Column_Useremail = "Useremail";
    private static final String User_Column_UserPhoneNumber = "UserPhoneNumber";



    public UserDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Database_Name, factory, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_User_Table = "Create table " + Table_User + "(" + User_Column_ID + " Integer Primary Key ," +
                User_Column_UserName + " TEXT, " + User_Column_Password + " TEXT, " + User_Column_Useremail + "TEXT, " +
                User_Column_UserPhoneNumber  + " TEXT)";
        db.execSQL(Create_User_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //user loader
    public String loadUserHandler(){
        String result = "";
        String query = "Select * From " + Table_User;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor  = db.rawQuery(query, null);
        while (cursor.moveToNext()){
            int userID = cursor.getInt(0);
            String userName = cursor.getString(1);
            String userPassword = cursor.getString(2);
            String useremail = cursor.getString(3);
            String userPhoneNumber = cursor.getString(4);
            result = userID  + " " + userName + " " + userPassword + " " + useremail + " " + userPhoneNumber + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    //add user
    public void addUser_Handler(User user){
        ContentValues values = new ContentValues();
        values.put(User_Column_ID, user.getUserID());
        values.put(User_Column_UserName, user.getUserName());
        values.put(User_Column_Password, user.getUserPassword());
        values.put(User_Column_Useremail, user.getUserEmail());
        values.put(User_Column_UserPhoneNumber, user.getUserPhoneNumber());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(Table_User, null, values);
        db.close();

    }

    //get user
    public User findUserHander(int userID){
        String query = "Select * From " + Table_User + " Where " + User_Column_ID + " = " + userID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserID(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPassword(cursor.getString(2));
            user.setUserEmail(cursor.getString(3));
            user.setUserPhoneNumber(cursor.getString(4));
            cursor.close();
        } else{
            user = null;
        }
        db.close();
        return user;
    }

    //Delete user
    public boolean deleteUserHandler(int userID){
        boolean result = false;
        String query = "Select * From " + Table_User + " Where " + User_Column_ID + " = "  + userID;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Game game = new Game();
        if (cursor.moveToFirst()) {
            game.setGameID(cursor.getInt(0));
            db.delete(Table_User, User_Column_ID + " = " , new String[] {String.valueOf(userID)});
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }

    public boolean updateUserHandler(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(User_Column_ID, user.getUserID());
        args.put(User_Column_UserName, user.getUserName());
        args.put(User_Column_Password, user.getUserPassword());
        args.put(User_Column_Useremail, user.getUserEmail());
        args.put(User_Column_UserPhoneNumber, user.getUserPhoneNumber());
        return db.update(Table_User, args, User_Column_ID + " = " + user.getUserID(), null) > 0;

    }

    public User UserExistsInDatabase(String username, String password)
    {
        String query = "Select * From " + Table_User + " Where " + User_Column_UserName+ " = " +username+ " And " + User_Column_Password + " = " + password;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setUserID(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setUserPassword(cursor.getString(2));
            user.setUserEmail(cursor.getString(3));
            user.setUserPhoneNumber(cursor.getString(4));
            cursor.close();
        } else{
            user = null;
        }
        db.close();
        return user;
    }

}
