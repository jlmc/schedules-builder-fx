package org.xine.qtime.server.rest.services;

import org.xine.qtime.server.rest.entities.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;

/**
 * The Class SubjectDaoImpl.
 */
@ApplicationScoped
public class SubjectDaoImpl implements SubjectDao {

    /** The Constant subjects. */
    private static final List<Subject> subjects = Collections.synchronizedList(new ArrayList<>(
            Arrays.asList(new Subject[] {new Subject(new Long(1L), "Aa", "a", "a A a"),
                    new Subject(new Long(2L), "Bb", "b", "b B b") })));

    /** The conter. */
    private static AtomicLong conter = new AtomicLong(3L);

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.server.rest.services.Dao#list()
     */
    @Override
    public List<Subject> list() {
        return new ArrayList<>(subjects);
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.server.rest.services.Dao#save(java.lang.Object)
     */
    @Override
    public Subject save(final Subject entity) {
        if (entity != null) {
            entity.setId(new Long(conter.incrementAndGet()));
            if (subjects.add(entity)) {
                return entity;
            }
        }
        throw new RuntimeException("invalid entity");
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.server.rest.services.Dao#read(java.lang.Long)
     */
    @Override
    public Subject read(final Long id) {
        if (id != null) {
            for (final Subject subject : subjects) {
                if (subject.getId().equals(id)) {
                    return subject;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.server.rest.services.Dao#delete(java.lang.Object)
     */
    @Override
    public void delete(final Subject entity) {
        if (entity == null || entity.getId() == null) {
            throw new RuntimeException("invalid entity");
        }

        Subject s = null;
        for (final Subject subject : subjects) {
            if (subject.getId().equals(entity.getId())) {
                s = subject;
            }
        }

        if (s != null) {
            subjects.remove(s);
        } else {
            throw new RuntimeException("entity no exists on System");
        }
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.server.rest.services.Dao#update(java.lang.Object)
     */
    @Override
    public Subject update(final Subject entity) {
        if (entity != null) {
            for (final Subject subject : subjects) {
                if (subject.getId().equals(entity.getId())) {
                    subject.setName(entity.getName());
                    subject.setDescription(entity.getDescription());
                    subject.setAcronym(entity.getAcronym());
                    return subject;
                }
            }
        }
        throw new RuntimeException("invalid entity");
    }

}
