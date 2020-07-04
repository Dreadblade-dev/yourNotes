package com.dreadblade.dao;

import com.dreadblade.entity.Note;
import com.dreadblade.entity.User;

import java.util.List;

/**
 * Interface of DAO objects, declare only methods specific for Note
 * The rest methods can be obtained by extending IDao interface
 */
public interface NoteDao extends IDao<Note> {
    Note create(User owner, String title, String content);
    List<Note> findAllByUser(User user);
    Note updateById(int id, String title, String content);
    void deleteAllByUser(User user);
}
