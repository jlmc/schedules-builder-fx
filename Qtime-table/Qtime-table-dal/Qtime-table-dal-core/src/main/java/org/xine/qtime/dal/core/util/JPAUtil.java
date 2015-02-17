package org.xine.qtime.dal.core.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Class JPAUtil.
 */
public final class JPAUtil {
    /** The Constant PERSISTENCE_UNIT_NAME. */
    public static final String PERSISTENCE_UNIT_NAME = "QtimeTablePU";

    /**
     * Instantiates a new JPA util.
     */
    private JPAUtil() {
        super();
    }

    /** The emf. */
    private static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    /**
     * Creates the entity manager.
     * @return the entity manager
     */
    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

}
