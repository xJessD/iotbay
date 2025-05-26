package model.dao;

import java.sql.*;
import java.util.*;
import model.*;

public class OrderDAO {
    private Connection conn;

    public OrderDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Create a new order and order lines
    public void createOrder(Order order, List<OrderLine> orderLines) throws SQLException {
        String insertOrderSQL = "INSERT INTO Orders (customerID, orderStatus, orderDate, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?)";
        String insertLineSQL = "INSERT INTO OrderLines (orderID, productID, quantity, requests, createdDate, updatedDate) VALUES (?, ?, ?, ?, ?, ?)";

        conn.setAutoCommit(false); // Begin transaction

        try (
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement lineStmt = conn.prepareStatement(insertLineSQL)
        ) {
            // Insert order
            orderStmt.setInt(1, order.getCustomerID());
            orderStmt.setString(2, order.getOrderStatus());
            orderStmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            orderStmt.setDate(4, new java.sql.Date(order.getCreatedDate().getTime()));
            orderStmt.setDate(5, new java.sql.Date(order.getUpdatedDate().getTime()));
            orderStmt.executeUpdate();

            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1);
            }

            // Insert order lines
            for (OrderLine line : orderLines) {
                lineStmt.setInt(1, orderID);
                lineStmt.setInt(2, line.getProductID());
                lineStmt.setInt(3, line.getQuantity());
                lineStmt.setString(4, line.getRequests());
                lineStmt.setDate(5, new java.sql.Date(line.getCreatedDate().getTime()));
                lineStmt.setDate(6, new java.sql.Date(line.getUpdatedDate().getTime()));
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

    // 2. Get all orders for a customer
    public List<Order> getOrdersByCustomer(int customerID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE customerID = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("orderID"),
                    rs.getInt("customerID"),
                    rs.getString("orderStatus"),
                    rs.getDate("orderDate"),
                    rs.getDate("createdDate"),
                    rs.getDate("updatedDate")
                );
                orders.add(order);
            }
        }
        return orders;
    }

    // 3. Cancel an order
    public void cancelOrder(int orderID) throws SQLException {
        String sql = "UPDATE Orders SET orderStatus = 'cancelled', updatedDate = CURRENT_DATE WHERE orderID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        }
    }

    // 4. Get all orders (ignores customerID)
public List<Order> getAllOrders() throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM Orders";

    try (Statement stmt = conn.createStatement()) {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Order order = new Order(
                rs.getInt("orderID"),
                rs.getInt("customerID"),
                rs.getString("orderStatus"),
                rs.getDate("orderDate"),
                rs.getDate("createdDate"),
                rs.getDate("updatedDate")
            );
            orders.add(order);
        }
    }
    return orders;
}

}

