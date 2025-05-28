package model.dao;

import java.sql.Connection;
import java.sql.Statement;

public class OrderDBSetup {
    public static void createTables(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            String orderTable = "CREATE TABLE Orders (" +
                "orderId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "userId INT, " +
                "orderDate DATE, " +
                "status VARCHAR(20), " +
                "total DOUBLE, " +
                "FOREIGN KEY (userId) REFERENCES Users(userId))";

            String orderLineTable = "CREATE TABLE OrderLines (" +
                "lineId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                "orderId INT, " +
                "productId INT, " +
                "quantity INT, " +
                "price DOUBLE, " +
                "FOREIGN KEY (orderId) REFERENCES Orders(orderId), " +
                "FOREIGN KEY (productId) REFERENCES Products(productId))";

            stmt.execute(orderTable);
            stmt.execute(orderLineTable);

            System.out.println("✅ Orders and OrderLines tables created successfully.");
        } catch (Exception e) {
            System.out.println("⚠️ Table creation failed:");
            e.printStackTrace();
        }
    }
}
