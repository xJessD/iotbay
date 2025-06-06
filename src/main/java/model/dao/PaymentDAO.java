package model.dao;

import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * PaymentDAO class to handle all payment-related database operations
 */
public class PaymentDAO {
    
    private Statement st;
    private Connection conn;
    
    public PaymentDAO(Connection conn) throws SQLException {
        this.conn = conn;
        this.st = conn.createStatement();
    }
    
    // Create a new payment record
    public int createPayment(int orderID, int customerID, Date paymentDate, String paymentMethod,
                           String paymentAmount, String billingStreetAddress, String billingPostcode,
                           String billingCity, String billingState, String billingPhoneNumber) throws SQLException {
        
        // Using prepared statement to prevent SQL injection
        String query = "INSERT INTO Payment (orderID, customerID, paymentDate, paymentMethod, " +
                "paymentAmount, billingStreetAddress, billingPostcode, billingCity, billingState, " +
                "billingPhoneNumber, paymentStatus, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, orderID);
        ps.setInt(2, customerID);
        ps.setDate(3, new java.sql.Date(paymentDate.getTime()));
        ps.setString(4, paymentMethod);
        ps.setString(5, paymentAmount);
        ps.setString(6, billingStreetAddress);
        ps.setString(7, billingPostcode);
        ps.setString(8, billingCity);
        ps.setString(9, billingState);
        ps.setString(10, billingPhoneNumber);
        ps.setString(11, "Pending"); // Default status is Pending
        
        // Set created and updated dates to current time
        Date now = new Date();
        ps.setDate(12, new java.sql.Date(now.getTime()));
        ps.setDate(13, new java.sql.Date(now.getTime()));
        
        ps.executeUpdate();
        
        // Get the generated payment ID
        ResultSet rs = ps.getGeneratedKeys();
        int paymentID = -1;
        if (rs.next()) {
            paymentID = rs.getInt(1);
        }
        
        ps.close();
        return paymentID;
    }
    
    // Overloaded createPayment method that accepts a Payment object
    public int createPayment(Payment payment) throws SQLException {
        return createPayment(
            payment.getOrderID(),
            payment.getCustomerID(),
            payment.getPaymentDate(),
            payment.getPaymentMethod(),
            payment.getPaymentAmount(),
            payment.getBillingStreetAddress(),
            payment.getBillingPostcode(),
            payment.getBillingCity(),
            payment.getBillingState(),
            payment.getBillingPhoneNumber()
        );
    }
    
    // Get payment by ID
    public Payment getPayment(int paymentID) throws SQLException {
        String query = "SELECT * FROM Payment WHERE paymentID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, paymentID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            java.util.Date paymentDate = parseDateString(rs.getString("paymentDate"));
            java.util.Date createdDate = parseDateString(rs.getString("createdDate"));
            java.util.Date updatedDate = parseDateString(rs.getString("updatedDate"));
            Payment payment = new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                rs.getInt("customerID"),
                paymentDate,
                rs.getString("paymentMethod"),
                rs.getString("paymentAmount"),
                rs.getString("billingStreetAddress"),
                rs.getString("billingPostcode"),
                rs.getString("billingCity"),
                rs.getString("billingState"),
                rs.getString("billingPhoneNumber"),
                rs.getString("paymentStatus"),
                createdDate,
                updatedDate
            );
            ps.close();
            return payment;
        }
        ps.close();
        return null;
    }
    
    // Get all payments for a customer
    public List<Payment> getPaymentsByCustomer(int customerID) throws SQLException {
        String query = "SELECT * FROM Payment WHERE customerID = ? ORDER BY paymentDate DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, customerID);
        ResultSet rs = ps.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            java.util.Date paymentDate = parseDateString(rs.getString("paymentDate"));
            java.util.Date createdDate = parseDateString(rs.getString("createdDate"));
            java.util.Date updatedDate = parseDateString(rs.getString("updatedDate"));
            payments.add(new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                customerID,
                paymentDate,
                rs.getString("paymentMethod"),
                rs.getString("paymentAmount"),
                rs.getString("billingStreetAddress"),
                rs.getString("billingPostcode"),
                rs.getString("billingCity"),
                rs.getString("billingState"),
                rs.getString("billingPhoneNumber"),
                rs.getString("paymentStatus"),
                createdDate,
                updatedDate
            ));
        }
        ps.close();
        return payments;
    }
    
    // Get all payments for an order
    public List<Payment> getPaymentsByOrder(int orderID) throws SQLException {
        String query = "SELECT * FROM Payment WHERE orderID = ? ORDER BY paymentDate DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, orderID);
        ResultSet rs = ps.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            java.util.Date paymentDate = parseDateString(rs.getString("paymentDate"));
            java.util.Date createdDate = parseDateString(rs.getString("createdDate"));
            java.util.Date updatedDate = parseDateString(rs.getString("updatedDate"));
            payments.add(new Payment(
                rs.getInt("paymentID"),
                orderID,
                rs.getInt("customerID"),
                paymentDate,
                rs.getString("paymentMethod"),
                rs.getString("paymentAmount"),
                rs.getString("billingStreetAddress"),
                rs.getString("billingPostcode"),
                rs.getString("billingCity"),
                rs.getString("billingState"),
                rs.getString("billingPhoneNumber"),
                rs.getString("paymentStatus"),
                createdDate,
                updatedDate
            ));
        }
        ps.close();
        return payments;
    }
    
    // Search payments by date range
    public List<Payment> searchPaymentsByDateRange(int customerID, Date startDate, Date endDate) throws SQLException {
        String query = "SELECT * FROM Payment WHERE customerID = ? AND paymentDate BETWEEN ? AND ? ORDER BY paymentDate DESC";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, customerID);
        ps.setDate(2, new java.sql.Date(startDate.getTime()));
        ps.setDate(3, new java.sql.Date(endDate.getTime()));
        ResultSet rs = ps.executeQuery();
        List<Payment> payments = new ArrayList<>();
        while (rs.next()) {
            java.util.Date paymentDate = parseDateString(rs.getString("paymentDate"));
            java.util.Date createdDate = parseDateString(rs.getString("createdDate"));
            java.util.Date updatedDate = parseDateString(rs.getString("updatedDate"));
            payments.add(new Payment(
                rs.getInt("paymentID"),
                rs.getInt("orderID"),
                customerID,
                paymentDate,
                rs.getString("paymentMethod"),
                rs.getString("paymentAmount"),
                rs.getString("billingStreetAddress"),
                rs.getString("billingPostcode"),
                rs.getString("billingCity"),
                rs.getString("billingState"),
                rs.getString("billingPhoneNumber"),
                rs.getString("paymentStatus"),
                createdDate,
                updatedDate
            ));
        }
        ps.close();
        return payments;
    }
    
    // Update payment details
    public boolean updatePayment(int paymentID, String paymentMethod, String paymentAmount,
                               String billingStreetAddress, String billingPostcode, String billingCity,
                               String billingState, String billingPhoneNumber) throws SQLException {
        
        // First check if payment is in Pending status
        String checkQuery = "SELECT paymentStatus FROM Payment WHERE paymentID = ?";
        PreparedStatement checkPs = conn.prepareStatement(checkQuery);
        checkPs.setInt(1, paymentID);
        ResultSet rs = checkPs.executeQuery();
        
        if (rs.next() && !rs.getString("paymentStatus").equals("Pending")) {
            checkPs.close();
            return false; // Cannot update if not in Pending status
        }
        checkPs.close();
        
        String query = "UPDATE Payment SET paymentMethod = ?, paymentAmount = ?, billingStreetAddress = ?, " +
                "billingPostcode = ?, billingCity = ?, billingState = ?, billingPhoneNumber = ?, " +
                "updatedDate = ? WHERE paymentID = ?";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, paymentMethod);
        ps.setString(2, paymentAmount);
        ps.setString(3, billingStreetAddress);
        ps.setString(4, billingPostcode);
        ps.setString(5, billingCity);
        ps.setString(6, billingState);
        ps.setString(7, billingPhoneNumber);
        ps.setDate(8, new java.sql.Date(new Date().getTime()));
        ps.setInt(9, paymentID);
        
        int rowsUpdated = ps.executeUpdate();
        ps.close();
        
        return rowsUpdated > 0;
    }
    
    // Overloaded updatePayment method that accepts a Payment object
    public boolean updatePayment(Payment payment) throws SQLException {
        return updatePayment(
            payment.getPaymentID(),
            payment.getPaymentMethod(),
            payment.getPaymentAmount(),
            payment.getBillingStreetAddress(),
            payment.getBillingPostcode(),
            payment.getBillingCity(),
            payment.getBillingState(),
            payment.getBillingPhoneNumber()
        );
    }
    
    // Delete payment record
    public boolean deletePayment(int paymentID) throws SQLException {
        // First check if payment is in Pending status
        String checkQuery = "SELECT paymentStatus FROM Payment WHERE paymentID = ?";
        PreparedStatement checkPs = conn.prepareStatement(checkQuery);
        checkPs.setInt(1, paymentID);
        ResultSet rs = checkPs.executeQuery();
        
        if (rs.next() && !rs.getString("paymentStatus").equals("Pending")) {
            checkPs.close();
            return false; // Cannot delete if not in Pending status
        }
        checkPs.close();
        
        String query = "DELETE FROM Payment WHERE paymentID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, paymentID);
        
        int rowsDeleted = ps.executeUpdate();
        ps.close();
        
        return rowsDeleted > 0;
    }
    
    // Confirm payment - change status from Pending to Confirmed
    public boolean confirmPayment(int paymentID) throws SQLException {
        String query = "UPDATE Payment SET paymentStatus = 'Confirmed', updatedDate = ? WHERE paymentID = ? AND paymentStatus = 'Pending'";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setDate(1, new java.sql.Date(new Date().getTime()));
        ps.setInt(2, paymentID);
        
        int rowsUpdated = ps.executeUpdate();
        ps.close();
        
        return rowsUpdated > 0;
    }
    
    // Helper method to convert ResultSet row to Payment object
    private Payment resultSetToPayment(ResultSet rs) throws SQLException {
        java.util.Date paymentDate = parseDateString(rs.getString("paymentDate"));
        java.util.Date createdDate = parseDateString(rs.getString("createdDate"));
        java.util.Date updatedDate = parseDateString(rs.getString("updatedDate"));
        return new Payment(
            rs.getInt("paymentID"),
            rs.getInt("orderID"),
            rs.getInt("customerID"),
            paymentDate,
            rs.getString("paymentMethod"),
            rs.getString("paymentAmount"),
            rs.getString("billingStreetAddress"),
            rs.getString("billingPostcode"),
            rs.getString("billingCity"),
            rs.getString("billingState"),
            rs.getString("billingPhoneNumber"),
            rs.getString("paymentStatus"),
            createdDate,
            updatedDate
        );
    }
    
    // Helper method to parse a date string (yyyy-MM-dd or yyyy-MM-dd HH:mm:ss) to java.util.Date
    private java.util.Date parseDateString(String dateStr) {
        if (dateStr == null) return null;
        try {
            if (dateStr.length() == 10) { // yyyy-MM-dd
                return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            } else if (dateStr.length() == 19) { // yyyy-MM-dd HH:mm:ss
                return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            }
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}