package ru.itis.dao;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T, U> {
    T save(Class clazz, T entity);
    Optional<T> findById(Class clazz, U id);
    List<T> findAll(Class clazz);
    void delete(T entity);
}
