package model.dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM Products";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int id = rs.getInt("productID");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            int stock = rs.getInt("stock");

            Product product = new Product(id, name, price, stock);
            products.add(product);
        }

        System.out.println("✅ ProductDAO: Loaded " + products.size() + " products from DB.");

        return products;
    }
}
