package com.dreadblade.servlets.email.utils;

/**
 * Exceptions for MailProperties
 */
public class NoMailPropertiesException extends RuntimeException {
    public NoMailPropertiesException() {
    }

    public NoMailPropertiesException(String message) {
        super(message);
    }
}
