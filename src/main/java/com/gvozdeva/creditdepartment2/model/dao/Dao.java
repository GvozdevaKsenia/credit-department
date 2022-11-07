package com.gvozdeva.creditdepartment2.model.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    T save(T entity);

    boolean delete(K id);

    List<T> findAll();

    Optional<T> findById(K id);

    Optional<T> findById(K id, Connection connection);

    void update(T entity);
}
