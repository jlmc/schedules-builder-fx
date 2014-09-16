package org.xine.qtime.dal.daos.impl.hibernate;

import org.xine.qtime.dal.daos.UserDao;
import org.xine.qtime.entities.User;

/**
 * The Class UserDaoHibernate.
 */
public class UserDaoHibernate extends GenericHibernateDAO<User, Long> implements UserDao {

    @Override
    public User getByUserName(final String username) {
        return (User) super.getSession().createQuery("From User u where u.credentials.username = :value").setParameter("value", username).setMaxResults(1).uniqueResult();

    }

}
