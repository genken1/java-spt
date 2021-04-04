package ru.mirea.practice14.dao;

import java.util.List;

public interface BaseOperations<T> {
    boolean save(T t);
    boolean remove(String name);
    List<T> getAll();
}
