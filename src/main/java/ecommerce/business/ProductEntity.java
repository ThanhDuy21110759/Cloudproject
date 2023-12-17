package ecommerce.business;

import ecommerce.data.ProductDB;
import jakarta.persistence.*;

import java.sql.*;
import java.util.*;


@Entity
@Table(name = "product", schema = "ecommerce")
public class ProductEntity {
    private static final Object lock = new Object();
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PRODUCTID")
    private long productid;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "PRODUCTAVGRATING")
    private Integer productavgrating;
    @Basic
    @Column(name = "PRODUCTIMG")
    private String productimg;
    @Basic
    @Column(name = "PRODUCTPRICE")
    private Double productprice;
    @Basic
    @Column(name = "PRODUCTTITTLE")
    private String producttittle;
    @Basic
    @Column(name = "PRODUCTTOTALSELLING")
    private Integer producttotalselling;
    @Basic
    @Column(name = "UPDATEDATE")
    private Timestamp updatedate;
    @Basic
    @Column(name = "FLASH_SALE_PRICE")
    private Double flashSalePrice;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FLASH_SALE_START")
    private Timestamp flashSaleStart;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FLASH_SALE_END")
    private Timestamp flashSaleEnd;
    @OneToOne
    @JoinColumn(name = "shopID")
    private ShopEntity shop;
    @OneToOne
    @JoinColumn(name = "categories")
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private List<LineitemsEntity> lineItems;


    //Init
    public ProductEntity(String name, double price){
        this.producttittle = name;
        this.productprice = price;

    }

    public ProductEntity() {

    }
    public void endFlashSale() {
        synchronized (lock) {
            this.flashSalePrice = null;
            this.flashSaleStart = null;
            this.flashSaleEnd = null;
        }
    }

    public Double getCurrentPrice() {
        synchronized (lock) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            if (flashSalePrice != null && now.after(flashSaleStart) && now.before(flashSaleEnd)) {
                return flashSalePrice;
            } else {
                return productprice;
            }
        }
    }

    public void startFlashSale(Double discountPerentage, Timestamp startTime, Timestamp endTime) {
        synchronized (lock) {
            this.flashSalePrice = this.productprice*(1-discountPerentage);
            this.flashSaleStart = startTime;
            this.flashSaleEnd = endTime;
        }

        long duration = endTime.getTime() - startTime.getTime();

        new Thread(() -> {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            endFlashSale();
        }).start();
    }


//    public void setProductimg(String path) {
//        try {
//            byte[] image = Files.readAllBytes(Paths.get(path));
//            this.productimg = image;
//
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    public void setProductimg(String nameImg){
        this.productimg = nameImg;
    }

    public String getProductimg() {
        return productimg;
    }
//    public String getProductimgBase64() {
//        return Base64.getEncoder().encodeToString(this.productimg);
//    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public ShopEntity getShop() {
        return shop;
    }
    public String getCategoryName() {
        return this.category.getName();
    }

    public void setCategory(CategoryEntity category){
        this.category = category;
    }
    public long getProductid() {
        return productid;
    }
    public void setProductid(long productid) {
        this.productid = productid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getProductavgrating() {
        return productavgrating;
    }
    public void setProductavgrating(Integer productavgrating) {
        this.productavgrating = productavgrating;
    }
    public Double getProductprice() {
        return productprice;
    }
    public void setProductprice(Double productprice) {
        this.productprice = productprice;
    }
    public String getProducttittle() {
        return producttittle;
    }
    public void setProducttittle(String producttittle) {
        this.producttittle = producttittle;
    }
    public Integer getProducttotalselling() {
        return producttotalselling;
    }
    public void setProducttotalselling(Integer producttotalselling) {
        this.producttotalselling = producttotalselling;
    }
    public Timestamp getUpdatedate() {
        return updatedate;
    }
    public void setUpdatedate(Timestamp updatedate) {
        this.updatedate = updatedate;
    }
    public Integer getAmountInven(Long id){
        return ProductDB.getAmountInventory(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (productid != that.productid) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (productavgrating != null ? !productavgrating.equals(that.productavgrating) : that.productavgrating != null)
            return false;
        if (productimg != null ? !productimg.equals(that.productimg) : that.productimg != null) return false;
        if (productprice != null ? !productprice.equals(that.productprice) : that.productprice != null) return false;
        if (producttittle != null ? !producttittle.equals(that.producttittle) : that.producttittle != null)
            return false;
        if (producttotalselling != null ? !producttotalselling.equals(that.producttotalselling) : that.producttotalselling != null)
            return false;
        if (updatedate != null ? !updatedate.equals(that.updatedate) : that.updatedate != null) return false;
        if (shop != null ? !shop.equals(that.shop) : that.shop != null) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (productid ^ (productid >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (productavgrating != null ? productavgrating.hashCode() : 0);
        result = 31 * result + (productimg != null ? productimg.hashCode() : 0);
        result = 31 * result + (productprice != null ? productprice.hashCode() : 0);
        result = 31 * result + (producttittle != null ? producttittle.hashCode() : 0);
        result = 31 * result + (producttotalselling != null ? producttotalselling.hashCode() : 0);
        result = 31 * result + (updatedate != null ? updatedate.hashCode() : 0);
        result = 31 * result + (shop != null ? shop.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}