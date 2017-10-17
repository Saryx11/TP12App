package com.example.benjaminlouis.tp12app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by benjaminlouis on 16/10/2017.
 */

public class UsersDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="Users.db";
    public static final int DB_Version=1;

    public UsersDBHelper(Context context){
        super(context, DB_NAME, null, DB_Version);
    }

    public static String getQueryCreate(){
        return "CREATE TABLE Users("
                +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"nom VARCHAR(30) NOT NULL,"
                +"prenom VARCHAR(20) NOT NULL,"
                +"age INTEGER NOT NULL,"
                +"metier VARCHAR(50) NOT NULL"
                +");";
    }

    public static String getQueryDrop(){
        return "DROP TABLE IF EXISTS Users";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getQueryCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(getQueryDrop());
            db.execSQL(getQueryCreate());

    }
}
