package org.australteca.dao;

import org.australteca.entity.EntityInterface;

import java.util.List;

/**
 * Created by tomi on 19/04/17.
 */
public interface Dao<T extends EntityInterface> {

    Integer add(T t);

    void delete(Integer id) throws IllegalArgumentException;

    T get(Integer id);

    void merge(T t);

    List<T> list();
}
