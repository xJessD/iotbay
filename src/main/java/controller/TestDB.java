package controller;

import model.dao.DBConnector;
import model.dao.OrderDBSetup;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            System.out.println("✅ Connected to database!");

            // Create Orders and OrderLines tables
            OrderDBSetup.createTables(conn);

            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Connection or Table Creation failed:");
            e.printStackTrace();
        }
    }
}

