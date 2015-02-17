package org.xine.qtime.dal.core.services;

import org.xine.qtime.dal.core.daos.SubjectDao;
import org.xine.qtime.dal.core.util.Transactional;
import org.xine.qtime.entities.Subject;

import java.util.List;

import javax.inject.Inject;

/**
 * The Class SubjectService.
 */
public class SubjectService {

    /** The dao. */
    @Inject
    private SubjectDao dao;

    /**
     * List.
     * @return the list
     */
    public List<Subject> list() {
        return this.dao.list();
    }

    /**
     * Read.
     * @param id
     *            the id
     * @return the subject
     */
    public Subject read(final Long id) {
        return this.dao.read(id);
    }

    /**
     * Save.
     * @param s
     *            the s
     * @return the subject
     */
    @Transactional
    public Subject save(final Subject s) {
        return this.dao.save(s);
    }

    /**
     * Update.
     * @param s
     *            the s
     * @return the subject
     */
    @Transactional
    public Subject update(final Subject s) {
        return this.dao.save(s);
    }

    /**
     * Delete.
     * @param s
     *            the s
     */
    @Transactional
    public void delete(final Subject s) {
        this.dao.delete(s);
    }

}
