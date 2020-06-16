package com.dreadblade.dao.jdbc;

import com.dreadblade.dao.BaseDao;
import com.dreadblade.dao.DaoException;
import com.dreadblade.dao.DaoFactory;
import com.dreadblade.dao.UserDao;
import com.dreadblade.entity.User;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A class that implements database operations with User entity
 */
public class JdbcUserDao extends BaseDao<User> implements UserDao {
    private final DaoFactory daoFactory;
    private static final Logger log = Logger.getLogger(JdbcUserDao.class.getName());

    public JdbcUserDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public User create(String user_name, String first_name, String last_name, String email, String password) throws DaoException {
        log.info("Creating new user with login \"" + user_name + "\".");
        String sqlCreateUser = "INSERT INTO users (user_name, first_name, last_name, email, password) values (?, ?, ?, ?, ?);";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlCreateUser, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user_name);
                statement.setString(2, first_name);
                statement.setString(3, last_name);
                statement.setString(4, email);
                statement.setString(5, password);
                statement.execute();
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getGeneratedKeys();
                    resultSet.next();
                    log.trace("Creating user to return");
                    user = new User(resultSet.getString("user_name"),
                            resultSet.getString("first_name"), resultSet.getString("last_name"),
                            resultSet.getString("email"), resultSet.getString("password"));
                    user.setId(resultSet.getInt("id"));
                    log.info("User with login \"" + user_name + "\" created!");
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
            log.error("Cannot create user", e);
            throw new DaoException("Cannot create user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning user with login \"" + user_name + "\".");
        return user;
    }

    @Override
    public User findById(int id) throws DaoException {
        log.info("Getting user with id \"" + id + "\".");
        String sqlGetUserById = "SELECT * FROM users WHERE id = ?;";

        User user = null;
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
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with id \"" + id + "\" was found!");
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
            log.error("Cannot get user", e);
            throw new DaoException("Cannot get user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning user with id \"" + id + "\".");
        return user;
    }

    @Override
    public User findByUsername(String user_name) throws DaoException {
        log.info("Getting user with username \"" + user_name + "\".");
        String sqlGetUserByUsername = "SELECT * FROM users WHERE user_name = ?;";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlGetUserByUsername);
                statement.setString(1, user_name);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with username \"" + user_name + "\" was found!");
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
            log.error("Cannot get user", e);
            throw new DaoException("Cannot get user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning user with username \"" + user_name + "\".");
        return user;
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        log.info("Getting user with email \"" + email + "\".");
        String sqlGetUserByEmail = "SELECT * FROM users WHERE email = ?;";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlGetUserByEmail);
                statement.setString(1, email);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with email \"" + email + "\" was found!");
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
            log.error("Cannot get user", e);
            throw new DaoException("Cannot get user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning user with email \"" + email + "\".");
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        log.info("Getting all users from table \"users\".");
        String sqlGetAllUsers = "SELECT * FROM users ORDER BY id;";

        List<User> users = new CopyOnWriteArrayList<User>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlGetAllUsers);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        log.trace("Creating user to add to the list");
                        User user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        users.add(user);
                        log.info("User with username \"" + user.getUsername() + "\" was found and" +
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
            log.error("Cannot get user", e);
            throw new DaoException("Cannot get user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning list of users.");
        return users;
    }

    @Override
    public User updateById(int id, String user_name, String first_name, String last_name, String email, String password) throws DaoException {
        log.info("Updating user with id \"" + id + "\".");
        String sqlUpdateUserById = "UPDATE users SET user_name = ?, first_name = ?, last_name = ?, email = ?, password = ? WHERE id = ?;";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlUpdateUserById);
                statement.setString(1, user_name);
                statement.setString(2, first_name);
                statement.setString(3, last_name);
                statement.setString(4, email);
                statement.setString(5, password);
                statement.setInt(6, id);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with id \"" + id + "\" was successfully updated!");
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
            log.error("Cannot update user", e);
            throw new DaoException("Cannot update user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning updated user with id \"" + id + "\".");
        return user;
    }

    @Override
    public User updateByUsername(String user_name, String first_name, String last_name, String email, String password) throws DaoException {
        log.info("Updating user with username \"" + user_name + "\".");
        String sqlUpdateUserByUsername = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ? WHERE user_name = ?;";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlUpdateUserByUsername);
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, email);
                statement.setString(4, password);
                statement.setString(5, user_name);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with username \"" + user_name + "\" was successfully updated!");
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
            log.error("Cannot update user", e);
            throw new DaoException("Cannot update user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning updated user with username \"" + user_name + "\".");
        return user;
    }

    @Override
    public User updateByEmail(String email, String user_name, String first_name, String last_name, String password) throws DaoException {
        log.info("Updating user with email \"" + email + "\".");
        String sqlUpdateUserByEmail = "UPDATE users SET user_name = ? first_name = ?, last_name = ?, password = ? WHERE email = ?;";

        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlUpdateUserByEmail);
                statement.setString(1, user_name);
                statement.setString(2, first_name);
                statement.setString(3, last_name);
                statement.setString(4, password);
                statement.setString(5, email);
                try {
                    log.trace("Getting result set");
                    resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        log.trace("Creating user to return");
                        user = new User(resultSet.getString("user_name"),
                                resultSet.getString("first_name"), resultSet.getString("last_name"),
                                resultSet.getString("email"), resultSet.getString("password"));
                        user.setId(resultSet.getInt("id"));
                        log.info("User with email \"" + email + "\" was successfully updated!");
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
            log.error("Cannot update user", e);
            throw new DaoException("Cannot update user", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
                log.trace("Connection closed");
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        log.trace("Returning updated user with email \"" + email + "\".");
        return user;
    }

    @Override
    public void deleteById(int id) throws DaoException {
        log.info("Deleting user with id \"" + id + "\".");
        String sqlDeleteUserById = "DELETE FROM users WHERE id = ?;";

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
                        log.info("User with id \"" + id + "\" was successfully deleted!");
                } finally {
                    try {
                        statement.close();
                        log.trace("Statement closed");
                    } catch (SQLException e) {
                        log.error("Cannot close statement", e);
                    }
                }
            } catch (SQLException e) {
                log.error("Cannot delete user", e);
                throw new DaoException("Cannot delete user", e);
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
    public void deleteByUsername(String user_name) throws DaoException {
        log.info("Deleting user with username \"" + user_name + "\".");
        String sqlDeleteUserByUsername = "DELETE FROM users WHERE user_name = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlDeleteUserByUsername);
                statement.setString(1, user_name);
                try {
                    if (statement.executeUpdate() == 1)
                        log.info("User with username \"" + user_name + "\" was successfully deleted!");
                } finally {
                    try {
                        statement.close();
                        log.trace("Statement closed");
                    } catch (SQLException e) {
                        log.error("Cannot close statement", e);
                    }
                }
            } catch (SQLException e) {
                log.error("Cannot delete user", e);
                throw new DaoException("Cannot delete user", e);
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
    public void deleteByEmail(String email) throws DaoException {
        log.info("Deleting user with email \"" + email + "\".");
        String sqlDeleteUserByEmail = "DELETE FROM users WHERE email = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            log.trace("Opening connection");
            connection = daoFactory.getConnection();
            try {
                log.trace("Creating prepared statement");
                statement = connection.prepareStatement(sqlDeleteUserByEmail);
                statement.setString(1, email);
                try {
                    if (statement.executeUpdate() == 1)
                        log.info("User with email \"" + email + "\" was successfully deleted!");
                } finally {
                    try {
                        statement.close();
                        log.trace("Statement closed");
                    } catch (SQLException e) {
                        log.error("Cannot close statement", e);
                    }
                }
            } catch (SQLException e) {
                log.error("Cannot delete user", e);
                throw new DaoException("Cannot delete user", e);
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