package main.java.controller;

import main.java.model.ProductCatalog;
import main.java.model.dao.DBConnector;
import main.java.model.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        try {
            Connection conn = DBConnector.getConnection();
            productDAO = new ProductDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to initialize ProductDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/productManage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No action specified.");
            return;
        }

        switch (action) {
            case "add":
                handleAdd(request);
                break;
            case "edit":
                handleEdit(request);
                break;
            case "delete":
                handleDelete(request);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action: " + action);
                return;
        }

        response.sendRedirect(request.getContextPath() + "/products");
    }

    private void handleAdd(HttpServletRequest request) {
        Product product = new Product(
                -1,
                request.getParameter("Name"),
                getOrDefault(request.getParameter("imageUrl"), "images/default.jpg"),
                request.getParameter("Description"),
                Double.parseDouble(request.getParameter("Price")),
                Integer.parseInt(request.getParameter("Quantity")),
                request.getParameter("favourited") != null
        );
        productDAO.add(product);
    }

    private void handleEdit(HttpServletRequest request) {
        Product product = new Product(
                Integer.parseInt(request.getParameter("ProductID")),
                request.getParameter("Name"),
                getOrDefault(request.getParameter("imageUrl"), "images/default.jpg"),
                request.getParameter("Description"),
                Double.parseDouble(request.getParameter("Price")),
                Integer.parseInt(request.getParameter("Quantity")),
                request.getParameter("favourited") != null
        );
        productDAO.update(product);
    }

    private void handleDelete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("ProductID"));
        productDAO.delete(id);
    }

    private String getOrDefault(String value, String defaultValue) {
        return (value == null || value.trim().isEmpty()) ? defaultValue : value;
    }
}
