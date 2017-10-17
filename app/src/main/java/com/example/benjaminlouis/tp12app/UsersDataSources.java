package com.example.benjaminlouis.tp12app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by benjaminlouis on 16/10/2017.
 */

public class UsersDataSources {
    private final UsersDBHelper helper;
    private SQLiteDatabase db;

    public UsersDataSources(Context context){
        helper=new UsersDBHelper(context);
    }

    public SQLiteDatabase getDb() {
        if(db==null)this.open();
        return db;
    }

    public void open() {
        db=helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
    }

    public UserDAO newUserDAO(){
        return new UserDAO(this);
    }
}
