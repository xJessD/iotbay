package model;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private int paymentID;
    private int orderID;
    private int customerID;
    private Date paymentDate;
    private String paymentMethod;
    private String paymentAmount;
    private String billingStreetAddress;
    private String billingPostcode;
    private String billingCity;
    private String billingState;
    private String billingPhoneNumber;
    private String paymentStatus;
    private Date createdDate;
    private Date updatedDate;

    
    public Payment() {}

    public Payment(int paymentID, int orderID, int customerID, Date paymentDate, String paymentMethod,
            String paymentAmount, String billingStreetAddress, String billingPostcode, String billingCity,
            String billingState, String billingPhoneNumber, String paymentStatus, Date createdDate, Date updatedDate) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.billingStreetAddress = billingStreetAddress;
        this.billingPostcode = billingPostcode;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingPhoneNumber = billingPhoneNumber;
        this.paymentStatus = paymentStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentAmount() {
        return paymentAmount;
    }
    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public String getBillingStreetAddress() {
        return billingStreetAddress;
    }
    public void setBillingStreetAddress(String billingStreetAddress) {
        this.billingStreetAddress = billingStreetAddress;
    }
    public String getBillingPostcode() {
        return billingPostcode;
    }
    public void setBillingPostcode(String billingPostcode) {
        this.billingPostcode = billingPostcode;
    }
    public String getBillingCity() {
        return billingCity;
    }
    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }
    public String getBillingState() {
        return billingState;
    }
    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }
    public String getBillingPhoneNumber() {
        return billingPhoneNumber;
    }
    public void setBillingPhoneNumber(String billingPhoneNumber) {
        this.billingPhoneNumber = billingPhoneNumber;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
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