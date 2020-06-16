package com.dreadblade.dao;

import com.dreadblade.dao.jdbc.JdbcNoteDao;
import com.dreadblade.dao.jdbc.JdbcUserDao;
import com.dreadblade.dao.utils.PropertiesDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Нужен, чтобы получить доступ к конкретному Dao объекту
 */

public class DaoFactory implements IDaoFactory {
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private String url;
    private String username;
    private String password;
    private static DaoFactory instance;

    private DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {
        if (instance != null)
            return instance;

        PropertiesDB properties = new PropertiesDB();

        String url = properties.getProperty(PROPERTY_URL);
        String username = properties.getProperty(PROPERTY_USERNAME);
        String password = properties.getProperty(PROPERTY_PASSWORD);

        instance = new DaoFactory(url, username, password);

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    public UserDao getUserDao() {
        return new JdbcUserDao(this);
    }

    public NoteDao getNoteDao() {
        return new JdbcNoteDao(this);
    }
}