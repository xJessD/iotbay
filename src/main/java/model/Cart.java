package model;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable {
    private int cartID;
    private int customerID;
    private boolean isActive;
    private Date createdDate;
    private Date updatedDate;

    public Cart() {
    }
    
    public Cart(int cartID, int customerID, boolean isActive, Date createdDate, Date updatedDate) {
        this.cartID = cartID;
        this.customerID = customerID;
        this.isActive = isActive;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getCartID() {
        return cartID;
    }
    public void setCartID(int cartID) {
        this.cartID = cartID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
