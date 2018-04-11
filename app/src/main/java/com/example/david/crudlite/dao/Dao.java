package com.example.david.crudlite.dao;

import android.os.Parcelable;

import java.util.List;

/*
 * Use models for only the database, hence models that implements Parcelable i.e UserItem
 */
public interface Dao<ITEM> {
    public List<ITEM> select();
    public ITEM selectById(String id);
    public void insert(ITEM object);
    public void update(ITEM object);
    public void delete(ITEM object);
}
