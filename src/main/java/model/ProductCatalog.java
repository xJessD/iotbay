package com.IoTBay.dao;

import com.IoTBay.Models.Product;
import com.IoTBay.Models.ProductView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private final Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ProductView> getAllProductViews() {
        List<ProductView> products = new ArrayList<>();
        String query = "SELECT p.*, c.categoryName, COALESCE(p.imageUrl, 'images/default.jpg') as imageUrl, " +
                       "COALESCE(p.favourited, false) as favourited FROM PRODUCT p " +
                       "LEFT JOIN CATEGORY c ON p.categoryID = c.categoryID";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getInt("Stock"),
                        rs.getBigDecimal("Price"),
                        rs.getString("Description"),
                        rs.getInt("CategoryID")
                );

                ProductView view = new ProductView(
                        product,
                        rs.getString("categoryName"),
                        rs.getString("imageUrl"),
                        rs.getBoolean("favourited")
                );
                products.add(view);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }

    public void insertProduct(Product product, String imageUrl, boolean favourited) {
        String query = "INSERT INTO PRODUCT (Name, Stock, Price, Description, CategoryID, imageUrl, favourited) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getStock());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getCatID());
            stmt.setString(6, imageUrl);
            stmt.setBoolean(7, favourited);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProduct(Product product, String imageUrl, boolean favourited) {
        String query = "UPDATE PRODUCT SET Name = ?, Stock = ?, Price = ?, Description = ?, " +
                       "CategoryID = ?, imageUrl = ?, favourited = ? WHERE ProductID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getStock());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getCatID());
            stmt.setString(6, imageUrl);
            stmt.setBoolean(7, favourited);
            stmt.setInt(8, product.getProId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteProduct(int productID) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM PRODUCT WHERE ProductID = ?")) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product findProductById(int id) {
        String query = "SELECT * FROM PRODUCT WHERE ProductID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("ProductID"),
                        rs.getString("Name"),
                        rs.getInt("Stock"),
                        rs.getBigDecimal("Price"),
                        rs.getString("Description"),
                        rs.getInt("CategoryID")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
