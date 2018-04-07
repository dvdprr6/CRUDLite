package com.example.david.crudlite.dao;

import java.util.List;

public interface Dao<T> {
    public List<T> select();
    public T selectById(String id);
    public void insert(T object);
    public void update(T object);
    public void delete(T object);
}
