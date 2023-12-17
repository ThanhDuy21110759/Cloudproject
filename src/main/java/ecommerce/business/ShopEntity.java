package ecommerce.business;

import jakarta.persistence.*;

@Entity
@Table(name = "shop", schema = "ecommerce")
public class ShopEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "SHOPID")
    private long shopid;
    @Basic
    @Column(name = "SHOPLOCATION")
    private String shoplocation;
    @Basic
    @Column(name = "SHOPNAME")
    private String shopname;
    @Basic
    @Column(name = "SHOPMANAGERID")
    private long shopmanagerid;

    public long getShopid() {
        return shopid;
    }
    public void setShopid(long shopid) {
        this.shopid = shopid;
    }
    public String getShoplocation() {
        return shoplocation;
    }
    public void setShoplocation(String shoplocation) {
        this.shoplocation = shoplocation;
    }
    public String getShopname() {
        return shopname;
    }
    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    public long getShopmanager() {
        return shopmanagerid;
    }
    public void setShopmanager(long shopmanagerid) {
        this.shopmanagerid = shopmanagerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopEntity that = (ShopEntity) o;

        if (shopid != that.shopid) return false;
        if (shoplocation != null ? !shoplocation.equals(that.shoplocation) : that.shoplocation != null) return false;
        if (shopname != null ? !shopname.equals(that.shopname) : that.shopname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (shopid ^ (shopid >>> 32));
        result = 31 * result + (shoplocation != null ? shoplocation.hashCode() : 0);
        result = 31 * result + (shopname != null ? shopname.hashCode() : 0);
        return result;
    }
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.clear();

        ProductEntity product = em.find(ProductEntity.class, 24);
        product.setProductimg("src/main/resources/22.jpg");
        em.persist(product);


        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}

