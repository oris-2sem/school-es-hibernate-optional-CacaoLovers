package ru.itis.dao;

import java.util.List;

public interface EntityDao<T, U> {
    U save(T entity);
    T getEntityById(Class clazz, U id);
    List<T> getEntities(Class clazz);
    void delete(T entity);
    void update(T entity);
}
