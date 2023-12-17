package ecommerce.business;

import jakarta.persistence.*;

@Entity
@Table(name = "customer", schema = "ecommerce")
public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CUSTOMERID")
    private long customerid;
    @Basic
    @Column(name = "CUSTOMERADDRESS")
    private String customeraddress;
    @Basic
    @Column(name = "CUSTOMERNAME")
    private String customername;
    @Basic
    @Column(name = "CUSTOMERPHONENUMBER")
    private String customerphonenumber;
    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @Basic
    @Column(name = "ROLE")
    private String role;
    @Basic
    @Column(name = "USERNAME")
    private String username;

    //Init
    public CustomerEntity(String name, String password){
        this.username = name;
        this.password = password;
    }

    public CustomerEntity() {

    }

    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerphonenumber() {
        return customerphonenumber;
    }

    public void setCustomerphonenumber(String customerphonenumber) {
        this.customerphonenumber = customerphonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (customerid != that.customerid) return false;
        if (customeraddress != null ? !customeraddress.equals(that.customeraddress) : that.customeraddress != null)
            return false;
        if (customername != null ? !customername.equals(that.customername) : that.customername != null) return false;
        if (customerphonenumber != null ? !customerphonenumber.equals(that.customerphonenumber) : that.customerphonenumber != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (customerid ^ (customerid >>> 32));
        result = 31 * result + (customeraddress != null ? customeraddress.hashCode() : 0);
        result = 31 * result + (customername != null ? customername.hashCode() : 0);
        result = 31 * result + (customerphonenumber != null ? customerphonenumber.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
