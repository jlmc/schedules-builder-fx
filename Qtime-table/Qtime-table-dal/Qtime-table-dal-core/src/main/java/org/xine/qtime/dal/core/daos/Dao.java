package org.xine.qtime.dal.core.daos;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface Dao.
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface Dao<T, ID extends Serializable> {

    /**
     * Save.
     * @param entity
     *            the entity
     * @return the t
     */
    T save(T entity);

    /**
     * Update.
     * @param entity
     *            the entity
     * @return the t
     */
    T update(T entity);

    /**
     * Read.
     * @param id
     *            the id
     * @return the t
     */
    T read(ID id);

    /**
     * List.
     * @return the list
     */
    List<T> list();

    /**
     * Delete.
     * @param entity
     *            the entity
     */
    void delete(T entity);

}
