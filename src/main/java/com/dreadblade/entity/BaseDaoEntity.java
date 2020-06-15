package com.dreadblade.entity;

/**
 * Базовая entity для DAO.
 * Каждая entity описывает одну и только одну таблицу.
 * Entity наследуются от этого абстрактного класса
 */
abstract public class BaseDaoEntity {
    @Override
    abstract public String toString();
}
