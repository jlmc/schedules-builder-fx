package org.xine.qtime.dal.daos;

/**
 * A factory for creating DAO objects.
 */
public abstract class DAOFactory {

    /**
     * Creates a standalone DAOFactory that returns unmanaged DAO
     * beans for use in any environment Hibernate has been configured
     * for. Uses HibernateUtil/SessionFactory and Hibernate context
     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
     * and transaction scoped.
     */
    public static final Class<?> HIBERNATE = org.xine.qtime.dal.daos.impl.HibernateDAOFactory.class;

    /**
     * Factory method for instantiation of concrete factories.
     * @param factory
     *            the factory
     * @return the dAO factory
     */
    public static DAOFactory instance(final Class<?> factory) {
        try {
            return (DAOFactory) factory.newInstance();
        } catch (final Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }

    // Add your DAO interfaces here
    /**
     * Gets the user dao.
     * @return the user dao
     */
    public abstract UserDao getUserDao();

    // public abstract CategoryDAO getCategoryDAO();

    // public abstract CommentDAO getCommentDAO();

    // public abstract ShipmentDAO getShipmentDAO();
}
