package ecommerce.data;

import ecommerce.business.CustomerEntity;
import ecommerce.business.ProductEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Random;

import static org.eclipse.persistence.logging.LogCategory.length;

public class CustomerDB {
    public static boolean isLogin = false;
    public static boolean isRemember = false;
    public static CustomerEntity loginedCustomer;

    public static void insert(CustomerEntity customer) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(customer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(CustomerEntity customer) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(customer);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(CustomerEntity customer) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(customer));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    //init
    public static CustomerEntity getCustomerById(Long cusId) {
        EntityManager em = DBUtil.getEntityManager();
        CustomerEntity customer = null;
        try {
            customer = em.find(CustomerEntity.class, cusId);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return customer;
    }

    public static boolean checkLogin(String uName, String pass){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT c FROM CustomerEntity c WHERE c.username = '" + uName +"' AND c.password = '" + pass + "'";
        TypedQuery<ProductEntity> q = em.createQuery(qString, ProductEntity.class);
        try {
            if(q.getSingleResult() != null){
                isLogin = true;
                return true;
            }
            else{
                isLogin = false;
                return false;
            }
        } catch (NoResultException e) {
            return false;
        } finally {
            em.close();
        }
    }

    public static CustomerEntity getCustomerByUserName(String uName) {
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT c FROM CustomerEntity c WHERE c.username = '" + uName + "'";
        TypedQuery<CustomerEntity> q = em.createQuery(qString, CustomerEntity.class);
        try {
            return q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }
    //Random forgot password
    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            sb.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return sb.toString();
    }
    public static void changePassword(String email, String newPassword) {
        EntityManager em = DBUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Assuming that the 'username' field is used to store the email
            CustomerEntity customer = em.createQuery(
                            "SELECT c FROM CustomerEntity c WHERE c.username = :email", CustomerEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();

            if (customer != null) {
                customer.setPassword(newPassword);
                em.persist(customer);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    public static Boolean checkExistUsername(String username) {
        EntityManager em = DBUtil.getEntityManager();
        Boolean exists = false;
        try {
            CustomerEntity customer = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.username = :username", CustomerEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            exists = customer != null;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return exists;
    }

    public static Long getCustomerIdByEmail(String email) {
        EntityManager em = DBUtil.getEntityManager();
        Long customerId = null;
        try {
            CustomerEntity customer =
                    em.createQuery("SELECT c FROM CustomerEntity c WHERE c.username = :email", CustomerEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (customer != null) {
                customerId = customer.getCustomerid();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return customerId;
    }
}
