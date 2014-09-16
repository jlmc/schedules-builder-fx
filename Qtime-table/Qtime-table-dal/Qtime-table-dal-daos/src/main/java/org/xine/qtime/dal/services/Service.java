package org.xine.qtime.dal.services;

import java.util.List;

/**
 * The Interface Service.
 * @param <T>
 *            the generic type
 */
public interface Service<T> {

    /**
     * Find.
     * @param clazz
     *            the clazz
     * @param id
     *            the id
     * @return the t
     */
    public T find(Class<T> clazz, Long id);

    /**
     * Find all.
     * @param clazz
     *            the clazz
     * @return the list
     */
    public List<T> findAll(Class<T> clazz);

    /**
     * Save.
     * @param t
     *            the t
     * @return the t
     */
    public T save(T t);

    /**
     * Save all.
     * @param l
     *            the l
     */
    public void saveAll(List<T> l);

    /**
     * Delete.
     * @param t
     *            the t
     */
    public void delete(T t);

    /**
     * Refresh.
     * @param t
     *            the t
     */
    public void refresh(T t);

}
