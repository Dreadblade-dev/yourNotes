package com.dreadblade.entity;

/**
 * This entity describes users table
 *
 *
 * SQL:
 * CREATE TABLE users(
 * id SERIAL,
 * user_name VARCHAR(32) NOT NULL UNIQUE,
 * first_name VARCHAR(255) NOT NULL,
 * last_name VARCHAR(255) NOT NULL,
 * email VARCHAR(255) NOT NULL UNIQUE,
 * password VARCHAR(255) NOT NULL
 * );
 */

public class User extends BaseDaoEntity {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public User(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        return "User " + id + "\nLogin: " + username;
    }

    @Override
    public int hashCode() {
        int hash = 47;
        hash = hash * 45 + id;
        hash = hash * 43 + username.hashCode();
        hash = hash * 41 + firstName.hashCode();
        hash = hash * 39 + lastName.hashCode();
        hash = hash * 37 + password.hashCode();
        hash = hash * 35 + email.hashCode();
        return Math.abs(hash);
    }
}
