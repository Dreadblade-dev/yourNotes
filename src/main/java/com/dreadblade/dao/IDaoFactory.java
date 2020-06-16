package com.dreadblade.dao;

/**
 * Интерфейс DaoFactory.
 */

public interface IDaoFactory {
    UserDao getUserDao();
    NoteDao getNoteDao();
}
