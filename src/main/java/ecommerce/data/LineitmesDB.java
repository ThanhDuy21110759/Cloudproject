package ecommerce.data;

import ecommerce.business.CartEntity;
import ecommerce.business.CustomerEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.business.ProductEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class LineitmesDB {
    public static String pagePosition = "1";

    public static void insert(LineitemsEntity lineitems) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(lineitems);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(LineitemsEntity lineitems) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(lineitems);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(LineitemsEntity lineitems) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(lineitems));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static List<LineitemsEntity> getLineItemsByUserId(Long userId) {
        EntityManager em = DBUtil.getEntityManager();
        return em.createQuery(
                        "SELECT l FROM LineitemsEntity l WHERE l.billId IN " +
                                "(SELECT b.billid FROM BillEntity b WHERE b.customerId = :userId)",
                        LineitemsEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    public static List<LineitemsEntity> getLineItemsNull(Long id){
        EntityManager em = DBUtil.getEntityManager();
        return em.createQuery(
                        "SELECT l FROM LineitemsEntity l WHERE l.billId IN " +
                                "(SELECT b.billid FROM BillEntity b WHERE b.customerId = :userId) AND l.status IS NULL",
                        LineitemsEntity.class)
                .setParameter("userId", id)
                .getResultList();
    }
    public static List<LineitemsEntity> getLineItemsACCEPT(Long id){
        EntityManager em = DBUtil.getEntityManager();
        return em.createQuery(
                        "SELECT l FROM LineitemsEntity l WHERE l.billId IN " +
                                "(SELECT b.billid FROM BillEntity b " +
                                "WHERE b.customerId = :userId) AND l.status = 'ACCEPT' ",
                        LineitemsEntity.class)
                .setParameter("userId", id)
                .getResultList();
    }
    public static List<LineitemsEntity> getLineItemsDONE(Long id){
        EntityManager em = DBUtil.getEntityManager();
        return em.createQuery(
                        "SELECT l FROM LineitemsEntity l WHERE l.billId IN " +
                                "(SELECT b.billid FROM BillEntity b " +
                                "WHERE b.customerId = :userId) AND l.status = 'DONE' ",
                        LineitemsEntity.class)
                .setParameter("userId", id)
                .getResultList();
    }
    public static List<LineitemsEntity> getLineItemsCANCEL(Long id){
        EntityManager em = DBUtil.getEntityManager();
        return em.createQuery(
                        "SELECT l FROM LineitemsEntity l WHERE l.billId IN " +
                                "(SELECT b.billid FROM BillEntity b " +
                                "WHERE b.customerId = :userId) AND l.status = 'CANCEL' ",
                        LineitemsEntity.class)
                .setParameter("userId", id)
                .getResultList();
    }

    public static LineitemsEntity getLineItemById(Long id) {
        EntityManager em = DBUtil.getEntityManager();
        LineitemsEntity lineItem = null;
        try {
            lineItem = em.find(LineitemsEntity.class, id);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return lineItem;
    }
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.clear();

        CustomerEntity cus1 = em.find(CustomerEntity.class, 1);
        List<LineitemsEntity> list =
                LineitmesDB.getLineItemsByUserId(1L);

        for (LineitemsEntity l : list){
            System.out.println(l.getProduct().getProducttittle() + "\n");
        }


        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}
