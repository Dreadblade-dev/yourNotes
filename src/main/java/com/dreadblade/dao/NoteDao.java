package com.dreadblade.dao;

import com.dreadblade.entity.Note;
import com.dreadblade.entity.User;

import java.util.List;

/**
 * Интерфейсы DAO объектов, объявлять только методы find()
 * Остальные можно получить, добавив наследование от
 * интерфейса IDao
 */

public interface NoteDao extends IDao<Note> {
    Note create(User owner, String title, String content);
    List<Note> findAllByUser(User user);
    Note updateById(int id, String title, String content);
    void deleteAllByUser(User user);
}
