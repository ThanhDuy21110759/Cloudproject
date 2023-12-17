package ecommerce.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    private static EntityManagerFactory entityManagerFactory;
    public static EntityManager getEntityManager(){
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()){
            entityManagerFactory =
                    Persistence.createEntityManagerFactory("ecommerce");
        }
        return  entityManagerFactory.createEntityManager();
    }
}