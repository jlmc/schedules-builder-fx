package org.xine.qtime.dal.core.daos;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.xine.qtime.entities.Subject;

public class SubjectDao implements Dao<Subject, Long> {

    @Inject
    private EntityManager entityManager;

    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Subject save(final Subject entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public Subject update(final Subject entity) {
        return this.entityManager.merge(entity);
    }

    @Override
    public Subject read(final Long id) {
        return this.entityManager.find(Subject.class, id);
    }

    @Override
    public List<Subject> list() {
        return this.entityManager.createQuery("from Subject").getResultList();

    }

    @Override
    public void delete(final Subject entity) {
        this.entityManager.remove(this.entityManager.find(Subject.class, entity.getId()));
    }

}
