package com.dreadblade.servlets.email.utils;

public class NoMailPropertiesException extends RuntimeException {
    public NoMailPropertiesException() {
    }

    public NoMailPropertiesException(String message) {
        super(message);
    }
}
