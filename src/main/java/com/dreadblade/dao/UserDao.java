package com.dreadblade.dao;

import com.dreadblade.entity.User;

/**
 * Interface of DAO objects, declare only methods specific for User
 * The rest methods can be obtained by extending IDao interface
 */
public interface UserDao extends IDao<User> {
    User create(String user_name, String first_name, String last_name, String email, String password) throws DaoException;
    User findByUsername(String user_name) throws DaoException;
    User findByEmail(String email) throws DaoException;
    User updateById(int id, String user_name, String first_name, String last_name, String email, String password) throws DaoException;
}
