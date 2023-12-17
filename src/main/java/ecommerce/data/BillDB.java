package ecommerce.data;

import ecommerce.business.BillEntity;
import ecommerce.business.LineitemsEntity;
import ecommerce.business.ProductEntity;
import ecommerce.util.DBUtil;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class BillDB {
    public static void insert(BillEntity bill) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(bill);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void update(BillEntity bill) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(bill);
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public static void delete(BillEntity bill) {
        EntityManager em = DBUtil.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.remove(em.merge(bill));
            trans.commit();
        } catch (Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    public static BillEntity getBillById(Long billId){
        EntityManager em = DBUtil.getEntityManager();
        try {
            return em.find(BillEntity.class, billId);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return null;
    }
    //Check status bill sanpham duy nhat (neu chua hoan thanh ko xoa sp)
    public static boolean checkBill(Long productId){
        EntityManager em = DBUtil.getEntityManager();
        try {
            // Tìm tất cả các LineitemEntity chứa product
            String q = "SELECT l FROM LineitemsEntity l WHERE l.product.productid = :productId";
            TypedQuery<LineitemsEntity> query = em.createQuery(q, LineitemsEntity.class);
            query.setParameter("productId", productId);
            List<LineitemsEntity> lineItems = query.getResultList();

            // Kiểm tra từng LineitemsEntity
            for (LineitemsEntity lineItem : lineItems) {
                BillEntity bill = BillDB.getBillById(lineItem.getBillId());
                if (bill != null) {
                    String status = bill.getStatus();
                    if ("INQUEUE".equals(status)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return false;
    }
    public static List<LineitemsEntity> getLineItemsByBillId(Long billId){
        EntityManager em = DBUtil.getEntityManager();
        List<LineitemsEntity> lineItems = new ArrayList<>();
        try {
            String q = "SELECT l FROM LineitemsEntity l " +
                    "WHERE l.billId = :billId";
            TypedQuery<LineitemsEntity> query = em.createQuery(q, LineitemsEntity.class);
            query.setParameter("billId", billId);
            lineItems = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return lineItems;
    }

    //return ListLineItems
    public static List<LineitemsEntity> getLineItemsInQueue(List<ProductEntity> listProduct){
        EntityManager em = DBUtil.getEntityManager();
        List<LineitemsEntity> lineItemsInQueue = new ArrayList<>();
        try {
            for (ProductEntity product : listProduct) {
                // Find all LineitemsEntity containing the product
                String q = "SELECT l FROM LineitemsEntity l " +
                        "WHERE l.product.productid = :productId";
                TypedQuery<LineitemsEntity> query = em.createQuery(q, LineitemsEntity.class);
                query.setParameter("productId", product.getProductid());
                List<LineitemsEntity> lineItems = query.getResultList();

                // Check each LineitemsEntity
                for (LineitemsEntity lineItem : lineItems) {
                    BillEntity bill = BillDB.getBillById(lineItem.getBillId());
                    if (bill != null) {
                        String status = bill.getStatus();
                        if ("INQUEUE".equals(status)
                                && lineItem.getStatus() == null) {
                            lineItemsInQueue.add(lineItem);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
        return lineItemsInQueue;
    }

    //update status all bill
    public static void updateBillStatus() {
        EntityManager em = DBUtil.getEntityManager();
        try {
            // Get all bills
            String q = "SELECT b FROM BillEntity b";
            TypedQuery<BillEntity> query = em.createQuery(q, BillEntity.class);
            List<BillEntity> bills = query.getResultList();

            // Check each bill
            for (BillEntity bill : bills) {
                List<LineitemsEntity> lineItems = bill.getLineItems();

                // Assume all line items are accepted until proven otherwise
                boolean allAccepted = true;

                for (LineitemsEntity lineItem : lineItems) {
                    if (!"ACCEPT".equals(lineItem.getStatus())) {
                        allAccepted = false;
                        break;
                    }
                }

                // If all line items are accepted, set the bill status to COMPLETE
                if (allAccepted) {
                    bill.setStatus("COMPLETE");
                    BillDB.update(bill);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            em.close();
        }
    }

    public static Long lastId() {
        EntityManager em = DBUtil.getEntityManager();
        try {
            Query query = em.createQuery("SELECT MAX(b.billid) FROM BillEntity b");
            Long lastId = (Long) query.getSingleResult();
            return lastId + 1;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            em.close();
        }
    }
}
