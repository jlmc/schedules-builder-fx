package org.xine.qtime.dal.core.util;

import org.junit.Ignore;
import org.junit.Test;
import org.xine.qtime.entities.Subject;

import javax.persistence.EntityManager;

public class JPAUtilTest {

    @Test
    @Ignore
    public void test() {
        final EntityManager em = JPAUtil.createEntityManager();

        final Subject subject = new Subject();
        subject.setName("AM2");
        subject.setDescription("Analise MatematicaII");

        em.getTransaction().begin();
        em.merge(subject);

        em.getTransaction().commit();

        em.close();
    }

}
