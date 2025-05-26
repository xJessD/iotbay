package model.dao;

import java.sql.Connection;
import java.sql.Statement;

public class OrderDBSetup {
    public static void createTables(Connection conn) {
        try (Statement stmt = conn.createStatement()) {

            String orderTable = "CREATE TABLE IF NOT EXISTS Orders (" +
                "orderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customerID INTEGER, " +
                "orderStatus TEXT, " +
                "orderDate DATE, " +
                "createdDate DATE, " +
                "updatedDate DATE" +
            ")";

            String orderLineTable = "CREATE TABLE IF NOT EXISTS OrderLines (" +
                "lineID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "orderID INTEGER, " +
                "productID INTEGER, " +
                "quantity INTEGER, " +
                "requests TEXT, " +
                "createdDate DATE, " +
                "updatedDate DATE" +
            ")";
            
            String productTable = "CREATE TABLE IF NOT EXISTS Products (" +
            "productID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL, " +
            "price REAL NOT NULL, " +
            "stock INTEGER NOT NULL" +
            ")";

            stmt.execute(productTable);

            // Insert dummy products (can later update stock)
            stmt.execute("INSERT INTO Products (name, price, stock) VALUES ('Smart Sensor', 49.99, 25)");
            stmt.execute("INSERT INTO Products (name, price, stock) VALUES ('IoT Gateway', 129.95, 15)");
            stmt.execute("INSERT INTO Products (name, price, stock) VALUES ('WiFi Plug', 19.99, 50)");


            stmt.execute(orderTable);
            stmt.execute(orderLineTable);

            System.out.println("✅ Orders and OrderLines tables created successfully.");
        } catch (Exception e) {
            System.out.println("⚠️ Table creation failed:");
            e.printStackTrace();
        }
    }
}
