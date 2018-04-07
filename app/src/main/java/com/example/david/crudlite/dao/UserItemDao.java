package com.example.david.crudlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.crudlite.database.DatabaseHelper;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.table.UserTable;

import java.util.List;

public class UserItemDao implements Dao<UserItem> {

    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase sqliteDatabase;

    public UserItemDao(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<UserItem> select() {
        return null;
    }

    @Override
    public UserItem selectById(String id) {
        return null;
    }

    @Override
    public void insert(UserItem userItem) {
        ContentValues values = userItem.toValues();
        sqliteDatabase = databaseHelper.getWritableDatabase();
        sqliteDatabase.insert(UserTable.TABLE_USERS, null, values);
    }

    @Override
    public void update(UserItem userItem) {

    }

    @Override
    public void delete(UserItem userItem) {

    }
}
