package com.dreadblade.dao;

import java.sql.SQLException;

/**
 * Свой тип ошибок для DAO
 */
public class DaoException extends RuntimeException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }
}
