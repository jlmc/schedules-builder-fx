package org.xine.qtime.dal.daos.impl.hibernate;

import org.xine.qtime.dal.daos.SubjectDao;
import org.xine.qtime.entities.Subject;

public class SubjectDaoHibernate extends GenericHibernateDAO<Subject, Long> implements SubjectDao {

    public SubjectDaoHibernate() {
        super();
    }
}
