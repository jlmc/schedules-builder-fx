package org.xine.qtime.dal.daos.impl;

import org.hibernate.Session;
import org.xine.qtime.dal.daos.DAOFactory;
import org.xine.qtime.dal.daos.UserDao;
import org.xine.qtime.dal.daos.impl.hibernate.GenericHibernateDAO;
import org.xine.qtime.dal.daos.impl.hibernate.SessionFactoryUtils;
import org.xine.qtime.dal.daos.impl.hibernate.UserDaoHibernate;

/**
 * A factory for creating HibernateDAO objects.
 */
public class HibernateDAOFactory extends DAOFactory {
    /**
     * Instantiate dao.
     * @param daoClass
     *            the dao class
     * @return the generic hibernate dao
     */
    @SuppressWarnings({"static-method" })
    private GenericHibernateDAO<?, ?> instantiateDAO(final Class<?> daoClass) {
        try {
            @SuppressWarnings("rawtypes")
            final GenericHibernateDAO dao = (GenericHibernateDAO) daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (final Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }

    /**
     * Gets the current session.
     * @return the current session
     */
    private static Session getCurrentSession() {
        return SessionFactoryUtils.getCurrentSession();
    }

    /*
     * (non-Javadoc)
     * @see org.xine.qtime.dal.daos.DAOFactory#getUserDao()
     */
    @Override
    public UserDao getUserDao() {
        return (UserDao) instantiateDAO(UserDaoHibernate.class);
    }
}
