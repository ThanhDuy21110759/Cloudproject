package ecommerce.business;

import jakarta.persistence.*;
import java.io.Serializable;

import java.util.List;

@Entity
@Table(name = "cart", schema = "ecommerce")
public class CartEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CARTID")
    private long cartid;
    @Basic
    @Column(name = "TOTALPRICE")
    private Double totalprice;
    @Basic
    @Column(name = "customerID")
    private Long customerId;
    @OneToMany(mappedBy = "cart", cascade=CascadeType.ALL)
    private List<LineitemsEntity> lineItems;

    public List<LineitemsEntity> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineitemsEntity> lineItems) {
        this.lineItems = lineItems;
    }

    public long getCartid() {
        return cartid;
    }

    public void setCartid(long cartid) {
        this.cartid = cartid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

        if (cartid != that.cartid) return false;
        if (totalprice != null ? !totalprice.equals(that.totalprice) : that.totalprice != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (cartid ^ (cartid >>> 32));
        result = 31 * result + (totalprice != null ? totalprice.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        return result;
    }
}
