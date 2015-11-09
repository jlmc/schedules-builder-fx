package org.xine.qtime.dal.core.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JPAUtil implements Constants {

    private JPAUtil() {
        super();
    }

    private static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

}
