package com.example.david.crudlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.crudlite.database.DatabaseHelper;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.table.UserTable;

import java.util.ArrayList;
import java.util.List;

/*
 * DAO layer to persist the sqlite DB
 */
public class UserItemDao implements Dao<UserItem> {

    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase sqliteDatabase;

    public UserItemDao(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<UserItem> select() {
        List<UserItem> allUserItems = new ArrayList();
        sqliteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = sqliteDatabase.query(UserTable.TABLE_USERS, UserTable.ALL_COLUMNS,
                null, null, null, null, null);

        // Cursor to iterate through the rows
        while(cursor.moveToNext()){
            UserItem userItem = new UserItem();
            String userId = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_ID));
            String firstName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_LAST_NAME));
            String email = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_EMAIL));

            userItem.setId(userId);
            userItem.setFirstName(firstName);
            userItem.setLastName(lastName);
            userItem.setEmail(email);

            allUserItems.add(userItem);

        }

        cursor.close();

        return allUserItems;
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
        ContentValues values = userItem.toValues();
        sqliteDatabase = databaseHelper.getWritableDatabase();
        sqliteDatabase.update(UserTable.TABLE_USERS, values, UserTable.COLUMN_ID + " = ?", new String[]{userItem.getId()});

    }

    @Override
    public void delete(UserItem userItem) {
        sqliteDatabase = databaseHelper.getWritableDatabase();
        sqliteDatabase.delete(UserTable.TABLE_USERS,UserTable.COLUMN_ID + " = ?", new String[]{userItem.getId()});
    }
}
