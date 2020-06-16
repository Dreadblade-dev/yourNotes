package com.dreadblade.dao;

import com.dreadblade.entity.User;

import java.util.List;

/**
 * Интерфейсы DAO объектов, объявлять только методы, специфичные для User
 * Остальные можно получить, добавив наследование от интерфейса IDao
 */

public interface UserDao extends IDao<User> {
    User create(String user_name, String first_name, String last_name, String email, String password) throws DaoException;
    User findByUsername(String user_name) throws DaoException;
    User findByEmail(String email) throws DaoException;
    List<User> findAll() throws DaoException;
    User updateById(int id, String user_name, String first_name, String last_name, String email, String password) throws DaoException;
    User updateByUsername(String user_name, String first_name, String last_name, String email, String password) throws DaoException;
    User updateByEmail(String email, String user_name, String first_name, String last_name, String password) throws DaoException;
    void deleteByUsername(String user_name) throws DaoException;
    void deleteByEmail(String email) throws DaoException;
}
