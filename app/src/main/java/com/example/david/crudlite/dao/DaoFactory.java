package com.example.david.crudlite.dao;

import android.content.Context;

public class DaoFactory {
    private Context context;
    private static DaoFactory instance;

    public DaoFactory(){

    }

    public static Dao getUserItemDao(Context context){
        return new UserItemDao(context);
    }
}
