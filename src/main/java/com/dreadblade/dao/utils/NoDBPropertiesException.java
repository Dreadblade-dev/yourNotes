package com.dreadblade.dao.utils;

public class NoDBPropertiesException extends RuntimeException {

    public NoDBPropertiesException() {
    }

    public NoDBPropertiesException(String message) {
        super(message);
    }
}
