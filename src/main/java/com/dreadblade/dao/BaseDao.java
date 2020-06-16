package com.dreadblade.dao;

import com.dreadblade.entity.BaseDaoEntity;

/**
 * Общий абстрактный класс, нужен для добавления функционала всем DAO
 * Здесь должны быть реализации всех методов IDao, если этот код является
 * универсальным для любой Entity
 */
public abstract class BaseDao<T extends BaseDaoEntity> implements IDao<T> {
}
