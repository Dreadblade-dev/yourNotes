package com.dreadblade.dao;

import com.dreadblade.entity.BaseDaoEntity;

/**
 * Интерфейс DAO с общими методами
 * Опционально интерфейс конкретного DAO может реализовывать его (если методы подходят)
 */
public interface IDao<T extends BaseDaoEntity> {
    T findById(int id) throws DaoException;
    void deleteById(int id) throws DaoException;
}
