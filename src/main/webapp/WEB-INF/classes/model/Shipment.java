package model;

import java.io.Serializable;
import java.util.Date;

public class Shipment implements Serializable {
    private int shipmentID;
    private String status;
    private String trackingNumber;
    private String streetAddress;
    private String postcode;
    private String city;
    private String state;
    private String deliveryAllocation;
    private Date deliveryDate;
    private Date createdDate;
    private Date updatedDate;
    
    public Shipment() {}
    
    public Shipment(int shipmentID, String status, String trackingNumber, String streetAddress, String postcode,
            String city, String state, String deliveryAllocation, Date deliveryDate, Date createdDate,
            Date updatedDate) {
        this.shipmentID = shipmentID;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.streetAddress = streetAddress;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
        this.deliveryAllocation = deliveryAllocation;
        this.deliveryDate = deliveryDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getShipmentID() {
        return shipmentID;
    }
    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getDeliveryAllocation() {
        return deliveryAllocation;
    }
    public void setDeliveryAllocation(String deliveryAllocation) {
        this.deliveryAllocation = deliveryAllocation;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
