package ecommerce.data;

import java.util.ArrayList;
import java.util.List;

import ecommerce.business.*;
import jakarta.persistence.*;

import ecommerce.util.*;

public class ProductDB {
	
    public static void insert(ProductEntity product) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(ProductEntity product) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();       
        try {
            em.merge(product);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(ProductEntity product) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();        
        try {
            // Xóa tất cả các InventoryEntity liên quan
            Query q1 = em.createQuery("DELETE FROM InventoryEntity i WHERE i.productId = :productId");
            q1.setParameter("productId", product.getProductid());
            q1.executeUpdate();

            //LineItems
            Query q2 = em.createQuery("DELETE FROM LineitemsEntity i WHERE i.product.productid = :productId");
            q2.setParameter("productId", product.getProductid());
            q2.executeUpdate();

            em.remove(em.merge(product));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }       
    }

    //Select all items of product
    public static List<ProductEntity> selectProduct() {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static ProductEntity getProductById(Long id) {
        EntityManager em = DBUtil.getEntityManager();
        try {
            return em.find(ProductEntity.class, id);
        } finally {
            em.close();
        }
    }
    public static List<ProductEntity> selectProductASC(){
    	EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p ORDER BY p.productprice ASC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static List<ProductEntity> selectProductDESC(){
    	EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p ORDER BY p.productprice DESC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }   
    public static List<ProductEntity> getProductPopularity(){
    	EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p "
        		+ "ORDER BY p.producttotalselling DESC, "
        		+ "p.productavgrating DESC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    
    //Get all Products from Location
    public static List<ProductEntity> getProductsAt(String location) {
        List<ShopEntity> shops = ShopDB.getShopsByLocation(location);
        List<ProductEntity> allProducts = new ArrayList<>();
        assert shops != null;
        for (ShopEntity shop : shops) {
            List<ProductEntity> products = ShopDB.getProductsByShop(shop.getShopid());
            assert products != null;
            allProducts.addAll(products);
        }
        return allProducts;
    }
    public static List<ProductEntity> getBestSellingProducts(){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p "
                + "ORDER BY p.producttotalselling DESC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static List<ProductEntity> getNewestProducts(){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p "
                + "ORDER BY p.updatedate DESC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }
    public static List<ProductEntity> getProductsRating(int rating){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p "
                + "WHERE p.productavgrating = :rating";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        q.setParameter("rating", rating);
        try {
            return q.getResultList();
        } catch (NoResultException e) {        	
            return null;
        } finally {
            em.close();
        }
    }    
    public static long getTotalProducts() {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT COUNT(p.productid) FROM ProductEntity p";
        TypedQuery<Long> q = em.createQuery(qString, Long.class);        
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {        	
            return 0;
        } finally {
            em.close();
        }
    }
    //categories
    public static List<ProductEntity> getProductsByCategoryId(long id) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p where p.category.id = :id";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        q.setParameter("id", id);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static List<ProductEntity> getProductsByCategoryName(String Name) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p where p.category.name = :Name";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        q.setParameter("Name", Name);
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static double calculateTotal(List<LineitemsEntity> lineItems) {
        double total = 0;
        for (LineitemsEntity item : lineItems) {
            total += item.getAmount() * item.getProduct().getProductprice();
        }
        return total + 10; //Shipping cost
    }
    //Recommendation
    public static List<ProductEntity> getList10Popular(){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p "
                + "ORDER BY p.producttotalselling DESC, "
                + "p.productavgrating DESC";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        q.setMaxResults(10); // Set the maximum number of results to 10
        try {
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static List<ProductEntity> getListHint(CustomerEntity customer){
        Recommendation test = new Recommendation();
        return test.getRecommendation(customer);
    }
    public static CategoryEntity getCategoryByName(String cateName) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT c FROM CategoryEntity c "
                + "WHERE c.name = '" + cateName + "'";
        TypedQuery<CategoryEntity> q = em.createQuery(qString, CategoryEntity.class);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static Integer getAmountInventory(Long productId){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT c FROM InventoryEntity c WHERE c.productId = :productId";
        TypedQuery<InventoryEntity> q = em.createQuery(qString, InventoryEntity.class);
        q.setParameter("productId", productId);
        InventoryEntity result = q.getSingleResult();
        return result.getAmount();
    }
    public static Long lastId() {
        EntityManager em = DBUtil.getEntityManager();
        try {
            Query query = em.createQuery("SELECT MAX(b.productid) FROM ProductEntity b");
            Long lastId = (Long) query.getSingleResult();
            return lastId;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }
}