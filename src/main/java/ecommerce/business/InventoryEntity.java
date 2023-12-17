package ecommerce.business;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory", schema = "ecommerce")
public class InventoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private long id;
    @JoinColumn(name = "productID")
    private Long productId;
    @Basic
    @Column(name = "AMOUNT")
    private Integer amount;

    public void setProduct(Long productId) {
        this.productId = productId;
    }

    public Long getProduct() {
        return productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryEntity that = (InventoryEntity) o;


        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;

        return true;
    }
}
