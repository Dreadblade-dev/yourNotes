package com.dreadblade.entity;

/**
 * Base DAO entity.
 * Every entity describes one table
 * Entity must contain getters and setters
 * Entities extends this class
 */
abstract public class BaseDaoEntity {
    @Override
    abstract public String toString();
}
