package org.xine.qtime.dal.daos.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * The Class SessionFactoryUtils.
 */
public final class SessionFactoryUtils {

    /** The Constant sessionFactory. */
    private static SessionFactory sessionfactory;

    static {

        // final Configuration conf = new Configuration();
        // conf.configure();
        // final ServiceRegistry serviceregistry = new ServiceRegistryBuilder().applySettings(conf.getProperties())
        // .buildServiceRegistry();
        // sessionfactory = conf.buildSessionFactory(serviceregistry);
        /*
         */

        final Configuration cnf = new Configuration().configure();
        final ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cnf.getProperties()).build();
        sessionfactory = cnf.buildSessionFactory(sr);
        /**/
    }

    /**
     * Instantiates a new session factory utils.
     */
    private SessionFactoryUtils() {
        super();
    }

    /**
     * Adds the annotated class.
     * @param conf
     *            the conf
     */
    private static void addAnnotatedClass(final Configuration conf) {
        // add anottations class
        // conf.addAnnotatedClass(Person.class);
    }

    /**
     * Gets the current session.
     * @return the current session
     */
    public static Session getCurrentSession() {
        return sessionfactory.getCurrentSession();
    }

    /**
     * Begin transaction.
     * @return the session
     */
    public static Session beginTransaction() {
        final Session hibernateSession = getCurrentSession();
        hibernateSession.beginTransaction();
        return hibernateSession;
    }

    /**
     * Commit transaction.
     */
    public static void commitTransaction() {
        getCurrentSession().getTransaction().commit();
    }

    /**
     * Rollback transaction.
     */
    public static void rollbackTransaction() {
        getCurrentSession().getTransaction().rollback();
    }

    /**
     * Close session.
     */
    public static void closeSession() {
        getCurrentSession().close();
    }

    /**
     * Creates the data base.
     */
    public static void createDataBase() {
        final Configuration conf = new Configuration();

        addAnnotatedClass(conf);

        conf.configure();
        new SchemaExport(conf).create(true, true);
    }

}
