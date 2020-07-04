package com.dreadblade.dao.utils;

/**
 * Exceptions for PropertiesDB
 */
public class NoDBPropertiesException extends RuntimeException {

    public NoDBPropertiesException() {
    }

    public NoDBPropertiesException(String message) {
        super(message);
    }
}
