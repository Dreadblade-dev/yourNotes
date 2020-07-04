package com.dreadblade.dao;

import com.dreadblade.entity.BaseDaoEntity;

/**
 * A general abstract class, needed to add functionality to all DAOs
 * There should be implementations of all IDao methods if this code is universal for any Entity
 */
public abstract class BaseDao<T extends BaseDaoEntity> implements IDao<T> {
}
