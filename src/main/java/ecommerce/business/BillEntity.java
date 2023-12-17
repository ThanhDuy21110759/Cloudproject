package ecommerce.business;

import ecommerce.data.CustomerDB;
import ecommerce.data.ProductDB;
import jakarta.persistence.*;
import java.util.List;
import java.sql.Timestamp;

@Entity
@Table(name = "bill", schema = "ecommerce")
public class BillEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "BILLID")
    private long billid;
    @Basic
    @Column(name = "BILLDATE")
    private Timestamp billdate;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "customerID")
    private Long customerId;
    @OneToMany(mappedBy = "billId", cascade=CascadeType.ALL)
    private List<LineitemsEntity> lineItems;

    public long getBillid() {
        return billid;
    }
    public void setBillid(long billid) {
        this.billid = billid;
    }
    public Timestamp getBilldate() {
        return billdate;
    }
    public void setBilldate(Timestamp billdate) {
        this.billdate = billdate;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName(){
        return CustomerDB.loginedCustomer.getUsername();
    }
    public List<LineitemsEntity> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineitemsEntity> lineItems, Long index) {
        this.lineItems = lineItems;
        for (LineitemsEntity line: lineItems){
            line.setBillId(index);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (billid != that.billid) return false;
        if (billdate != null ? !billdate.equals(that.billdate) : that.billdate != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = (int) (billid ^ (billid >>> 32));
        result = 31 * result + (billdate != null ? billdate.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        return result;
    }
}
