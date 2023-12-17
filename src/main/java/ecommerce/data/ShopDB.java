package ecommerce.data;

import java.util.List;

import ecommerce.business.ProductEntity;
import jakarta.persistence.*;

import ecommerce.business.ShopEntity;
import ecommerce.util.DBUtil;

public class ShopDB {

	
	public static void insert(ShopEntity shop) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(shop);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(ShopEntity shop) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(shop);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(ShopEntity shop) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.remove(em.merge(shop));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }           
    //Get all Shop object (filter Location)
    public static List<ProductEntity> getProductsByShop(long shopID) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p WHERE p.shop.shopid = :shopID";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        q.setParameter("shopID", shopID);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static List<ShopEntity> getShopsByLocation(String location) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT s FROM ShopEntity s WHERE s.shoplocation = :location";
        TypedQuery<ShopEntity> q = em.createQuery(qString, ShopEntity.class);
        q.setParameter("location", location);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    //Get all Shop from Locations
    public static List<String> getUniqueLocations() {
	    EntityManager em = DBUtil.getEntityManager();
	    String qString = "SELECT DISTINCT s.shoplocation FROM ShopEntity s";
	    TypedQuery<String> q = em.createQuery(qString, String.class);
	    try {
            return q.getResultList();
	    } catch (NoResultException e) {        	
	        return null;
	    } finally {
	        em.close();
	    }
    }
    public static Long getIdFromLocation(String location) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT s.shopid FROM ShopEntity s WHERE s.shoplocation = :location";
        TypedQuery<Long> q = em.createQuery(qString, Long.class);
        q.setParameter("location", location);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static ShopEntity getShopByManagerID(long userId){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT s FROM ShopEntity s WHERE s.shopmanagerid = '" + userId + "'";
        TypedQuery<ShopEntity> q = em.createQuery(qString, ShopEntity.class);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }
}
