package com.dreadblade.entity;

/**
 * This entity describes notes table
 *
 *
 * SQL:
 * CREATE TABLE notes(
 * id SERIAL,
 * owner_id int NOT NULL,
 * title VARCHAR(128) NOT NULL,
 * content VARCHAR(2048) NOT NULL
 * );
 */

public class Note extends BaseDaoEntity {
    private int id;
    private int ownerID;
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Note:\n" + title;
    }
}
