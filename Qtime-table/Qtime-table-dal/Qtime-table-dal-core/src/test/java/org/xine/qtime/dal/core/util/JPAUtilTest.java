package org.xine.qtime.dal.core.util;

import org.junit.Ignore;
import org.junit.Test;
import org.xine.qtime.entities.Subject;

import javax.persistence.EntityManager;

public class JPAUtilTest {

    @SuppressWarnings("static-method")
    @Test
    @Ignore
    public void mergeTest() {
        EntityManager em = JPAUtil.createEntityManager();

        Subject subject = em.find(Subject.class, Long.valueOf(1L));

        em.close();

        if (subject != null) {
            subject.setAcronym("DP");
            subject.setName("Design Patterns");
        } else {
            subject = new Subject("TEST", "T", "desc");
        }

        em = JPAUtil.createEntityManager();

        em.getTransaction().begin();
        em.merge(subject);
        em.getTransaction().commit();

        em.close();

    }

    @SuppressWarnings("static-method")
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
