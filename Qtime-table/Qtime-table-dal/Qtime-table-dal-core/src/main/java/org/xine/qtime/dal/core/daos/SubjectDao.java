package org.xine.qtime.dal.core.daos;

import org.xine.qtime.entities.Subject;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * The Class SubjectDao.
 */
public class SubjectDao implements Dao<Subject, Long> {

    /** The entity manager. */
    @Inject
    private EntityManager entityManager;

    /**
     * Sets the entity manager.
     * @param entityManager
     *            the new entity manager
     */
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.core.daos.Dao#save(java.lang.Object)
     */
    @Override
    public Subject save(final Subject entity) {
        return this.entityManager.merge(entity);
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.core.daos.Dao#update(java.lang.Object)
     */
    @Override
    public Subject update(final Subject entity) {
        return this.entityManager.merge(entity);
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.core.daos.Dao#read(java.io.Serializable)
     */
    @Override
    public Subject read(final Long id) {
        return this.entityManager.find(Subject.class, id);
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.core.daos.Dao#list()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Subject> list() {
        return this.entityManager.createQuery("from Subject").getResultList();

    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.core.daos.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(final Subject entity) {
        this.entityManager.remove(this.entityManager.find(Subject.class, entity.getId()));
    }

}
