package ecommerce.data;

import ecommerce.business.CategoryEntity;
import ecommerce.business.InventoryEntity;
import ecommerce.business.ProductEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class InventoryDB {
    public static InventoryEntity getInventoryById(Long id) {
        EntityManager em = DBUtil.getEntityManager();
        try {
            return em.find(InventoryEntity.class, id);
        } finally {
            em.close();
        }
    }

    public static void insert(InventoryEntity invent) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(invent);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(InventoryEntity invent) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(invent);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(InventoryEntity invent) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(invent));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
