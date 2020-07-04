package com.dreadblade.dao;

import com.dreadblade.entity.BaseDaoEntity;

/**
 * DAO interface with common methods
 * Optionally, the interface of a specific DAO can implement it
 */
public interface IDao<T extends BaseDaoEntity> {
    T findById(int id) throws DaoException;
    void deleteById(int id) throws DaoException;
}
