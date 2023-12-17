package ecommerce.business;

import jakarta.persistence.*;

@Entity
@Table(name = "lineitems", schema = "ecommerce")
public class LineitemsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;
    @JoinColumn(name = "billId")
    private Long billId;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @ManyToOne
    @JoinColumn(name = "lineCart")
    private CartEntity cart;
    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductEntity product;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    public CartEntity getCart() {
        return cart;
    }
    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
    public Long getBillId() {
        return this.billId;
    }
    public void setBillId(Long billId) {
        this.billId = billId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineitemsEntity that = (LineitemsEntity) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (billId != null ? !billId.equals(that.billId) : that.billId != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (billId != null ? billId.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
