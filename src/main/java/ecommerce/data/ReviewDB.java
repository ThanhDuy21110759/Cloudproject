package ecommerce.data;

import ecommerce.business.ProductEntity;
import ecommerce.business.ReviewEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReviewDB {
    public static void insert(ReviewEntity review) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(review);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(ReviewEntity review) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(review);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(ReviewEntity review) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(review));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    //init
    public static List<ReviewEntity> getReviewsByProductId(Long productId) {
        EntityManager em = DBUtil.getEntityManager();
        List<ReviewEntity> reviews = null;
        try {
            TypedQuery<ReviewEntity> query = em.createQuery("SELECT r FROM ReviewEntity r WHERE r.productId = :productId", ReviewEntity.class);
            query.setParameter("productId", productId);
            reviews = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return reviews;
    }

}
