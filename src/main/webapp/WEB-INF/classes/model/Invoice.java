package model;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
    private int invoiceID;
    private int paymentID;
    private int orderID;
    private int customerID;
    private double amount;
    private String title;
    private String description;
    private Date createdDate;
    private Date updatedDate;

    
    public Invoice() {}
    
    public Invoice(int invoiceID, int paymentID, int orderID, int customerID, double amount, String title,
            String description, Date createdDate, Date updatedDate) {
        this.invoiceID = invoiceID;
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.customerID = customerID;
        this.amount = amount;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getInvoiceID() {
        return invoiceID;
    }
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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