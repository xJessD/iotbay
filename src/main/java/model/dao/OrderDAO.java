package model.dao;
import java.util.Date;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import model.*;

public class OrderDAO {
    private Connection conn;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // Create a new order and order lines
    public void createOrder(Order order, List<OrderLine> orderLines) throws SQLException {
        String insertOrderSQL = "INSERT INTO Orders (customerID, orderStatus, orderDate, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?)";
        String insertLineSQL = "INSERT INTO OrderLines (orderID, productID, quantity, requests, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?, ?)";

        conn.setAutoCommit(false);

        try (
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement lineStmt = conn.prepareStatement(insertLineSQL)
        ) {
            orderStmt.setInt(1, order.getCustomerID());
            orderStmt.setString(2, order.getOrderStatus());
            orderStmt.setString(3, dateFormat.format(order.getOrderDate()));
            orderStmt.setString(4, dateFormat.format(order.getCreatedDate()));
            orderStmt.setString(5, dateFormat.format(order.getUpdatedDate()));
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1);
            }

            for (OrderLine line : orderLines) {
                lineStmt.setInt(1, orderID);
                lineStmt.setInt(2, line.getProductID());
                lineStmt.setInt(3, line.getQuantity());
                lineStmt.setString(4, line.getRequests());
                lineStmt.setString(5, dateFormat.format(line.getCreatedDate()));
                lineStmt.setString(6, dateFormat.format(line.getUpdatedDate()));
                lineStmt.addBatch();
            }

            lineStmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // Get orders by customer ID
    public List<Order> getOrdersByCustomer(int customerID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE customerID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(buildOrderFromResultSet(rs));
            }
        }
        return orders;
    }

    // Cancel order
    public void cancelOrder(int orderID) throws SQLException {
        String sql = "UPDATE Orders SET orderStatus = 'cancelled', updatedDate = CURRENT_DATE WHERE orderID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        }
    }

    // Get all orders (for all users)
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(buildOrderFromResultSet(rs));
            }
        }
        return orders;
    }

    // Helper to build Order object
    private Order buildOrderFromResultSet(ResultSet rs) throws SQLException {
        try {
            int orderID = rs.getInt("orderID");
            int customerID = rs.getInt("customerID");
            String status = rs.getString("orderStatus");

            Date orderDate = new Date(rs.getLong("orderDate"));
            Date created = new Date(rs.getLong("createdDate"));
            Date updated = new Date(rs.getLong("updatedDate"));

            return new Order(orderID, customerID, status, orderDate, created, updated);
        } catch (Exception e) {
            throw new SQLException("Error parsing date from database: " + e.getMessage(), e);
        }
    }
}
