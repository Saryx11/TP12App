package com.example.benjaminlouis.tp12app;

import android.content.ContentValues;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjaminlouis on 16/10/2017.
 */

public class UserDAO {
    private static final String COL_NOM="nom";
    private static final String COL_PRENOM="prenom";
    private static final String COL_AGE="age";
    private static final String COL_METIER="metier";
    private static final String COL_ID="id";
    private static final String TABLE_NAME="Users";
    private static UsersDataSources dataSource;

    public UserDAO(UsersDataSources dataSource){
        this.dataSource=dataSource;
    }



    public synchronized User create(User user){
        ContentValues values=new ContentValues();
        values.put(COL_NOM,user.getNom());
        values.put(COL_PRENOM,user.getPrenom());
        values.put(COL_AGE,user.getAge());
        values.put(COL_METIER,user.getMetier());

        int id=(int)this.dataSource.getDb().insert(TABLE_NAME,null,values);

        user.setId(id);
        return user;
    }

    public synchronized User update(User user){
        ContentValues values=new ContentValues();
        values.put(COL_NOM,user.getNom());
        values.put(COL_PRENOM,user.getPrenom());
        values.put(COL_AGE,user.getAge());
        values.put(COL_METIER,user.getMetier());

        String clause=COL_ID+"=?";
        String[] clauseArgs=new String []{String.valueOf(user.getId())};

        this.dataSource.getDb().update(TABLE_NAME,values,clause,clauseArgs);
        return user;
    }

    public synchronized void delete(User user){
        String clause=COL_ID+"=?";
        String[] clauseArgs=new String[]{String.valueOf(user.getId())};
        this.dataSource.getDb().delete(TABLE_NAME,clause,clauseArgs);
    }

    public User read(User user){
        String[]allColumns = new String []{COL_ID,COL_NOM,COL_PRENOM,COL_AGE,COL_METIER};
        String clause=COL_ID+"=?";
        String[] clauseArgs=new String[]{String.valueOf(user.getId())};
        Cursor cursor = dataSource.getDb().query(TABLE_NAME,allColumns,"ID=?",clauseArgs,null,null,null);
        cursor.moveToFirst();
        user.setNom(cursor.getString(1));
        user.setPrenom(cursor.getString(2));
        user.setAge(cursor.getInt(3));
        user.setMetier(cursor.getString(4));
        cursor.close();
        return user;
    }

    public List<User> readAll(){
        String[]allColumns = new String []{COL_ID,COL_NOM,COL_PRENOM,COL_AGE,COL_METIER};
        Cursor cursor = dataSource.getDb().query(TABLE_NAME,allColumns,null,null,null,null,null);
        List<User>users=new ArrayList<User>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            users.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                    cursor.getInt( 3),cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }
}
