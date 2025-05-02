package model;

import java.io.Serializable;
import java.util.Date;

public class CartItem implements Serializable{
    private int cartItemID;   
    private int cartID;   
    private int productID;   
    private int quantity; 
    private Date createdDate;
    private Date updatedDate;
    
    public CartItem() {}

    public CartItem(int cartItemID, int cartID, int productID, int quantity, Date createdDate, Date updatedDate) {
        this.cartItemID = cartItemID;
        this.cartID = cartID;
        this.productID = productID;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
    public int getCartItemID() {
        return cartItemID;
    }
    public void setCartItemID(int cartItemID) {
        this.cartItemID = cartItemID;
    }
    public int getCartID() {
        return cartID;
    }
    public void setCartID(int cartID) {
        this.cartID = cartID;
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
