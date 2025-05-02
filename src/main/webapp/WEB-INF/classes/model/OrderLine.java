package model;

import java.io.Serializable;
import java.util.Date;

public class OrderLine implements Serializable {
    private int orderID;
    private int productID;
    private int quantity;
    private String requests;
    private Date createdDate;
    private Date updatedDate;

    
    public OrderLine() {}

    public OrderLine(int orderID, int productID, int quantity, String requests, Date createdDate, Date updatedDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.requests = requests;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getRequests() {
        return requests;
    }
    public void setRequests(String requests) {
        this.requests = requests;
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
