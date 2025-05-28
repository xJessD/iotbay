package model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String accountType;
    private Date createdDate;
    private Date updatedDate;
    private int userID;

    
    // Empty constructor
    public User() {}

    // Constructor without dates
    public User(String firstName, String lastName, String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // Constructor with dates
    public User(String firstName, String lastName, String email, String password, String phoneNumber, Date createdDate, Date updatedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getUserID() {
        return userID;
    }

    public boolean isAdmin() {
        return "Admin".equals(this.accountType);
    }

    public boolean isStaff() {
        return "Staff".equals(this.accountType);
    }

    public int getId() {
        return this.userID;
    }

}
