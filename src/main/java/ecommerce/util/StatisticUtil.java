package ecommerce.util;

import ecommerce.business.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.*;

public class StatisticUtil {
    public static List<ProductEntity> getSearchProducts(String queryString){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p WHERE p.producttittle LIKE :queryString";
        TypedQuery<ProductEntity> query = em.createQuery(qString, ProductEntity.class);
        query.setParameter("queryString", "%" + queryString + "%");

        List<ProductEntity> searchResults = new ArrayList<>();
        try {
            searchResults = query.getResultList();
        } catch (NoResultException e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return searchResults;
    }
    public static List<ProductEntity> getBestSeller(List<ProductEntity> productList){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT li.product FROM LineitemsEntity li " +
                         "WHERE li.status = 'DONE' AND li.product IN :productList " +
                         "GROUP BY li.product ORDER BY SUM(li.amount) DESC";
        TypedQuery<ProductEntity> query = em.createQuery(qString, ProductEntity.class);
        query.setParameter("productList", productList);
        query.setMaxResults(3);

        List<ProductEntity> searchResults = new ArrayList<>();
        try {
            searchResults = query.getResultList();
        } catch (NoResultException e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return searchResults;
    }
    public static Map<String, Double> calculateRevenue(List<ProductEntity> productList){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT FUNCTION('DAYOFWEEK', b.billdate), SUM(li.amount * li.product.productprice) " +
                "FROM BillEntity b JOIN b.lineItems li " +
                "WHERE li.status= 'DONE' and li.product IN :productList AND FUNCTION('DAYOFYEAR', b.billdate) >= FUNCTION('DAYOFYEAR', CURRENT_DATE) - 7 " +
                "GROUP BY FUNCTION('DAYOFWEEK', b.billdate)";
        TypedQuery<Object[]> query = em.createQuery(qString, Object[].class);
        query.setParameter("productList", productList);

        Map<String, Double> revenueByDay = new HashMap<>();
        try {
            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                int dayOfWeek = (int) result[0];
                double revenue = (double) result[1];
                String dayName;
                switch (dayOfWeek) {
                    case 1: dayName = "Sunday"; break;
                    case 2: dayName = "Monday"; break;
                    case 3: dayName = "Tuesday"; break;
                    case 4: dayName = "Wednesday"; break;
                    case 5: dayName = "Thursday"; break;
                    case 6: dayName = "Friday"; break;
                    case 7: dayName = "Saturday"; break;
                    default: dayName = "Unknown";
                }
                revenueByDay.put(dayName, revenue);
            }
        } catch (NoResultException e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return revenueByDay;
    }
    public static Map<String, Double> calculateRevenueByMonth(List<ProductEntity> productList){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT FUNCTION('MONTH', b.billdate), SUM(li.amount * li.product.productprice) " +
                "FROM BillEntity b JOIN b.lineItems li " +
                "WHERE li.status = 'DONE' AND li.product IN :productList " +
                "GROUP BY FUNCTION('MONTH', b.billdate)";
        TypedQuery<Object[]> query = em.createQuery(qString, Object[].class);
        query.setParameter("productList", productList);

        Map<String, Double> revenueByMonth = new HashMap<>();
        try {
            List<Object[]> results = query.getResultList();
            for (Object[] result : results) {
                int month = (int) result[0];
                double revenue = (double) result[1];
                String monthName;
                switch (month) {
                    case 1: monthName = "January"; break;
                    case 2: monthName = "February"; break;
                    case 3: monthName = "March"; break;
                    case 4: monthName = "April"; break;
                    case 5: monthName = "May"; break;
                    case 6: monthName = "June"; break;
                    case 7: monthName = "July"; break;
                    case 8: monthName = "August"; break;
                    case 9: monthName = "September"; break;
                    case 10: monthName = "October"; break;
                    case 11: monthName = "November"; break;
                    case 12: monthName = "December"; break;
                    default: monthName = "Unknown";
                }
                revenueByMonth.put(monthName, revenue);
            }
        } catch (NoResultException e) {
            System.out.println(e);
        } finally {
            em.close();
        }

        return revenueByMonth;
    }
}
