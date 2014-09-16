package org.xine.qtime.dal.daos.impl.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.xine.qtime.dal.daos.GenericDAO;

/**
 * The Class GenericHibernateDAO.
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    /** The persistent class. */
    private final Class<T> persistentClass;

    /** The session. */
    private Session session;

    /**
     * The Constructor.
     */
    @SuppressWarnings("unchecked")
    public GenericHibernateDAO() {
        this.persistentClass = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    /**
     * Sets the session.
     * @param s
     *            the session
     */
    public void setSession(final Session s) {
        this.session = s;
    }

    /**
     * Gets the session.
     * @return the session
     */
    protected Session getSession() {
        if (this.session == null) {
            throw new IllegalStateException("Session has not been set on DAO before usage");
        }
        return this.session;
    }

    /**
     * Gets the persistent class.
     * @return the persistent class
     */
    public Class<T> getPersistentClass() {
        return this.persistentClass;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.GenericDAO#findById(java.io.Serializable, boolean)
     */
    @Override
    @SuppressWarnings({"unchecked" })
    public T findById(final ID id, final boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), id, LockOptions.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), id);
        }

        return entity;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.GenericDAO#findAll()
     */
    @Override
    public List<T> findAll() {
        return findByCriteria();
    }

    /**
     * Find by example.
     * @param exampleInstance
     *            the example instance
     * @param excludeProperty
     *            the exclude property
     * @return the list< t>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleInstance, final String[] excludeProperty) {
        final Criteria crit = getSession().createCriteria(getPersistentClass());
        final Example example = Example.create(exampleInstance);
        for (final String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.GenericDAO#makePersistent(java.lang.Object)
     */
    @Override
    public T makePersistent(final T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.GenericDAO#makeTransient(java.lang.Object)
     */
    @Override
    public void makeTransient(final T entity) {
        getSession().delete(entity);
    }

    /**
     * Flush.
     */
    public void flush() {
        getSession().flush();
    }

    /**
     * Clear.
     */
    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     * @param criterion
     *            the criterion
     * @return the list< t>
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(final Criterion... criterion) {
        final Criteria crit = getSession().createCriteria(getPersistentClass());
        for (final Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.Transactable#beginTransaction()
     */
    @Override
    public Transaction beginTransaction() {
        return this.getSession().beginTransaction();

    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.Transactable#commitTransaction()
     */
    @Override
    public void commitTransaction() {
        this.getSession().getTransaction().commit();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.das.dal.daos.Transactable#rollbackTransaction()
     */
    @Override
    public void rollbackTransaction() {
        this.getSession().getTransaction().rollback();
    }
}
