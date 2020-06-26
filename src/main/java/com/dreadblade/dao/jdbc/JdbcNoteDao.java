package com.dreadblade.dao.jdbc;

import com.dreadblade.dao.BaseDao;
import com.dreadblade.dao.DaoException;
import com.dreadblade.dao.DaoFactory;
import com.dreadblade.dao.NoteDao;
import com.dreadblade.entity.Note;
import com.dreadblade.entity.User;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class that implements database operations with Note entity
 */
public class JdbcNoteDao extends BaseDao<Note> implements NoteDao {
    private final DaoFactory daoFactory;
    private static Logger log = Logger.getLogger(JdbcNoteDao.class.getName());

    public JdbcNoteDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Note create(User owner, String title, String content) {
        log.info("Creating new note with title \"" + title + "\" and owner \"" + owner.getUsername() + "\".");
        String sqlCreateNote = "INSERT INTO notes (owner_id, title, content) VALUES (?, ?, ?);";

        Note note = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlCreateNote, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, owner.getId());
                statement.setString(2, title);
                statement.setString(3, content);
                statement.execute();
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    log.trace("Creating note to return");
                    note = new Note(resultSet.getString("title"), resultSet.getString("content"));
                    note.setOwnerID(owner.getId());
                    log.info("Note with title \"" + title + "\" and owner \"" + owner.getUsername() + "\" created!");
                } finally {
                    try {
                        if (resultSet != null)
                            resultSet.close();
                        log.trace("Result set closed");
                    } catch (SQLException e) {
                        log.error("Cannot close result set", e);
                    }
                }
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                    log.trace("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
        } catch (SQLException e) {
            log.error("Cannot create note", e);
            throw new DaoException("Cannot create note", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning note with title \"" + title + "\" and owner \"" + owner.getUsername() + "\".");
        return note;
    }

    @Override
    public List<Note> findAllByUser(User user) {
        log.info("Getting all notes from user \"" + user.getUsername() + "\".");
        String sqlGetAllUserNotes = "SELECT id, title, content FROM notes WHERE ? = owner_id ORDER BY id;";

        List<Note> notes = new CopyOnWriteArrayList<Note>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlGetAllUserNotes);
                statement.setInt(1, user.getId());
                try {
                    log.trace("Getting result set");
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        log.trace("Creating note to add to the notes list");
                        Note note = new Note(resultSet.getString("title"),
                                resultSet.getString("content"));
                        note.setId(resultSet.getInt("id"));
                        note.setOwnerID(user.getId());
                        notes.add(note);
                        log.trace("Note with title \"" + note.getTitle() + "\" was found and" +
                                " successfully added to the list!");
                    }

                } finally {
                    try {
                        if (resultSet != null)
                            resultSet.close();
                        log.trace("Result set closed");
                    } catch (SQLException e) {
                        log.error("Cannot close result set", e);
                    }
                }
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                    log.trace("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
        } catch (SQLException e) {
            log.error("Cannot get note", e);
            throw new DaoException("Cannot get note", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.info("Returning list of notes to user \"" + user.getUsername() + "\".");
        return notes;
    }

    @Override
    public Note findById(int id) throws DaoException {
        log.info("Getting note with id \"" + id + "\".");
        String sqlGetUserById = "SELECT * FROM notes WHERE id = ?;";

        Note note = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlGetUserById);
                statement.setInt(1, id);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        log.trace("Creating note to return");
                        note = new Note(resultSet.getString("title"), resultSet.getString("content"));
                        note.setOwnerID(resultSet.getInt("owner_id"));
                        note.setId(resultSet.getInt("id"));
                        log.info("Note with id \"" + id + "\" was found!");
                    }

                } finally {
                    try {
                        if (resultSet != null)
                            resultSet.close();
                        log.trace("Result set closed");
                    } catch (SQLException e) {
                        log.error("Cannot close result set", e);
                    }
                }
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                    log.trace("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
        } catch (SQLException e) {
            log.error("Cannot get note", e);
            throw new DaoException("Cannot get note", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning note with id \"" + id + "\".");
        return note;
    }

    @Override
    public Note updateById(int id, String title, String content) {
        log.info("Updating note with id \"" + id + "\".");
        String sqlUpdateUserById = "UPDATE notes SET title = ?, content = ? WHERE id = ? RETURNING *;";

        Note note = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlUpdateUserById);
                statement.setString(1, title);
                statement.setString(2, content);
                statement.setInt(3, id);
                statement.execute();
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getResultSet();
                    if (resultSet.next()) {
                        log.trace("Creating note to return");
                        note = new Note(resultSet.getString("title"), resultSet.getString("content"));
                        note.setId(resultSet.getInt("id"));
                        note.setOwnerID(resultSet.getInt("owner_id"));
                        log.info("Note with id \"" + id + "\" was successfully updated!");
                    }
                } finally {
                    try {
                        if (resultSet != null)
                            resultSet.close();
                        log.trace("Result set closed");
                    } catch (SQLException e) {
                        log.error("Cannot close result set", e);
                    }
                }
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                    log.trace("Statement closed");
                } catch (SQLException e) {
                    log.error("Cannot close statement", e);
                }
            }
        } catch (SQLException e) {
            log.error("Cannot update note", e);
            throw new DaoException("Cannot update note", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning updated note with id \"" + id + "\".");
        return note;
    }

    @Override
    public void deleteById(int id) throws DaoException {
        log.info("Deleting note with id \"" + id + "\".");
        String sqlDeleteUserById = "DELETE FROM notes WHERE id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlDeleteUserById);
                statement.setInt(1, id);
                try {
                    if (statement.executeUpdate() == 1)
                        log.info("Note with id \"" + id + "\" has been successfully deleted!");
                } finally {
                    try {
                        statement.close();
                        log.trace("Statement closed");
                    } catch (SQLException e) {
                        log.error("Cannot close statement", e);
                    }
                }
            } catch (SQLException e) {
                log.error("Cannot delete note", e);
                throw new DaoException("Cannot delete note", e);
            }
        } catch (SQLException e) {
            log.error("Cannot open connection");
            throw new DaoException("Cannot open connection", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public void deleteAllByUser(User user) {
        log.info("Deleting all user notes with id \"" + user.getId() + "\".");
        String sqlDeleteAllUserNotesById = "DELETE FROM notes WHERE owner_id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlDeleteAllUserNotesById);
                statement.setInt(1, user.getId());
                try {
                    if (statement.executeUpdate() >= 1)
                        log.info("All user notes with id \"" + user.getId() + "\" have been successfully deleted!");
                } finally {
                    try {
                        statement.close();
                        log.trace("Statement closed");
                    } catch (SQLException e) {
                        log.error("Cannot close statement", e);
                    }
                }
            } catch (SQLException e) {
                log.error("Cannot delete notes", e);
                throw new DaoException("Cannot delete notes", e);
            }
        } catch (SQLException e) {
            log.error("Cannot open connection");
            throw new DaoException("Cannot open connection", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}