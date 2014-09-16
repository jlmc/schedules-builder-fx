package org.xine.qtime.dal.daos;

import java.io.Serializable;
import java.util.List;

/**
 * The Interface GenericDAO.
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public interface GenericDAO<T, ID extends Serializable> extends Transactable {

    /**
     * Find by id.
     * @param id
     *            the id
     * @param lock
     *            the lock
     * @return the t
     */
    T findById(ID id, boolean lock);

    /**
     * Find all.
     * @return the list
     */
    List<T> findAll();

    /**
     * Find by example.
     * @param exampleInstance
     *            the example instance
     * @param excludeProperty
     *            the exclude property
     * @return the list
     */
    List<T> findByExample(T exampleInstance, String[] excludeProperty);

    /**
     * Make persistent.
     * @param entity
     *            the entity
     * @return the t
     */
    T makePersistent(T entity);

    /**
     * Make transient.
     * @param entity
     *            the entity
     */
    void makeTransient(T entity);

}
