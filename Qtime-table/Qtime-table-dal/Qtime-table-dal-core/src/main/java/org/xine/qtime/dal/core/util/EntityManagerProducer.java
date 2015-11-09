package org.xine.qtime.dal.core.util;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer implements Serializable, Constants {
    private static final long serialVersionUID = 1L;

    private final EntityManagerFactory factory;

    public EntityManagerProducer() {
        super();
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Produces
    @RequestScoped
    public EntityManager create() {
        return this.factory.createEntityManager();
    }

    public void close(@Disposes final EntityManager manager) {
        manager.close();
    }
}
