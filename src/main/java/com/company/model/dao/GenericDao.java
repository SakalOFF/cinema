package com.company.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable{

    boolean create (T entity);
    T findById(long id);
    List<T> findAll();
    void update(T entity);
    boolean delete(long id);
    void close();
}
