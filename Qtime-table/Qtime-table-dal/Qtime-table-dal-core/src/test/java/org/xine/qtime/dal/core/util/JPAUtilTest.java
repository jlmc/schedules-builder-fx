package org.xine.qtime.dal.core.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.xine.qtime.entities.ClassRoom;
import org.xine.qtime.entities.Subject;

public class JPAUtilTest {

    @Test
    @Ignore
    public void testGetClassRoom() {
        final EntityManager em = JPAUtil.createEntityManager();

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<ClassRoom> cq = cb.createQuery(ClassRoom.class);
        final Root<ClassRoom> rootEntry = cq.from(ClassRoom.class);
        final CriteriaQuery<ClassRoom> all = cq.select(rootEntry);
        final TypedQuery<ClassRoom> allQuery = em.createQuery(all);

        final List<ClassRoom> objects = allQuery.getResultList();

        System.out.println("size:" + objects.size());

    }

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
