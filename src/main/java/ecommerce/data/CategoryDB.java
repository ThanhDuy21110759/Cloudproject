package ecommerce.data;

import ecommerce.business.CategoryEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryDB {
    public static void insert(CategoryEntity i) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(i);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(CategoryEntity i) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(i);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(CategoryEntity i) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(i));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static List<String> getAllCategoryNames() {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT DISTINCT c.name FROM CategoryEntity c";
        TypedQuery<String> q = em.createQuery(qString, String.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

}
