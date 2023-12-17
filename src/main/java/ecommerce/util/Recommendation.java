package ecommerce.util;

import ecommerce.business.BillEntity;
import ecommerce.business.CustomerEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.business.ProductEntity;
import ecommerce.data.BillDB;
import ecommerce.data.ProductDB;
import jakarta.persistence.*;

import java.util.*;

public class Recommendation {

    //Recommend
    List<String> words = Arrays.asList(
            "iphone", "samsung", "laptop", "Phone", "apple", "galaxy", "clothes", "macbook",
            "Karants", "Brand", "Polo", "break"
    );
    private final W2V w2v = new W2V(words);

    public List<ProductEntity> recommend(List<ProductEntity> boughtProductIds, int numRecommendations) {

        double[]mainVec = new double[w2v.size()];
        for (ProductEntity product : boughtProductIds) {



            String titleWords = product.getProducttittle();
            String categoryWords = product.getCategoryName();


            double[] titleVector = w2v.getWordVector(titleWords);
            double[] categoryVector = w2v.getWordVector(categoryWords);

            for (int i = 0; i < w2v.size(); i++) {
                mainVec[i] += 0.5*titleVector[i] +  categoryVector[i];
            }

        }


        PriorityQueue<Map.Entry<ProductEntity, Double>> nearestProductsQueue = new PriorityQueue<>(
                Comparator.comparingDouble(Map.Entry::getValue)
        );


        EntityManager em =  DBUtil.getEntityManager();
        String qString = "SELECT p FROM ProductEntity p ";
        TypedQuery<ProductEntity> p = em.createQuery(qString, ProductEntity.class);
        try {
            List<ProductEntity> products = p.getResultList();
            for (ProductEntity product : products) {
                double[] title = w2v.getWordVector(product.getProducttittle());
                double[] category = w2v.getWordVector(product.getCategoryName());
                double[] productVector = new double[w2v.size()];

                for (int i=0;i<w2v.size();i++) {
                    productVector[i] = 0.5*title[i]+category[i];
                }


                double cosineSimilarity = cosineSimilarity(mainVec, productVector);

                if (nearestProductsQueue.size() < numRecommendations) {
                    nearestProductsQueue.add(new AbstractMap.SimpleEntry<>(product, cosineSimilarity));
                } else if (cosineSimilarity > nearestProductsQueue.peek().getValue()) {
                    nearestProductsQueue.poll();
                    nearestProductsQueue.add(new AbstractMap.SimpleEntry<>(product, cosineSimilarity));
                }
            }


        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }


        List<ProductEntity> recommendedProducts = new ArrayList<>();
        while (!nearestProductsQueue.isEmpty()) {
            recommendedProducts.add(0,nearestProductsQueue.poll().getKey());
        }


        return recommendedProducts;
    }

    private double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    //W2V and recommendation run
    public List<ProductEntity> getRecommendation(CustomerEntity customer){
        EntityManager em = DBUtil.getEntityManager();
        String qString = "SELECT b FROM BillEntity b ,CustomerEntity c " +
                         "WHERE :customerId = b.customerId";
        TypedQuery<BillEntity> b = em.createQuery(qString, BillEntity.class);
        b.setParameter("customerId", customer.getCustomerid());
        try {
            List<BillEntity> bill = b.getResultList();

            List<ProductEntity> boughtProducts = new ArrayList<>();
            for (BillEntity bi : bill){
                List<LineitemsEntity> productsList =
                        BillDB.getLineItemsByBillId(bi.getBillid());
                for(LineitemsEntity products : productsList){
                    boughtProducts.add(products.getProduct());
                }
            }
            return recommend(boughtProducts,10);

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.clear();

        CustomerEntity cus1 = em.find(CustomerEntity.class, 2);
        String qString = "SELECT b FROM BillEntity b ,CustomerEntity c " +
                "WHERE :customerId = b.customerId";
        TypedQuery<BillEntity> b = em.createQuery(qString, BillEntity.class);
        b.setParameter("customerId", cus1.getCustomerid());

        for (BillEntity bill: b.getResultList()){
            List<LineitemsEntity> productsList = BillDB.getLineItemsByBillId(bill.getBillid());
            System.out.println("Billid: " + bill.getBillid() + "/" + productsList.size());
            for (LineitemsEntity pro: productsList){
                System.out.println(pro.getProduct().getProducttittle());
            }
        }

        System.out.println("####Recccc");
        for (ProductEntity prod: ProductDB.getListHint(cus1)){
            System.out.println(prod.getProducttittle());
        }


        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
