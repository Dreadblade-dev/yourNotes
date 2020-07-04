package com.dreadblade.dao;

/**
 * DaoFactory interface should contain only declarations of getEntityDao methods
 */
public interface IDaoFactory {
    UserDao getUserDao();
    NoteDao getNoteDao();
}
