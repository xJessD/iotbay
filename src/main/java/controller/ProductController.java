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
public class ProductController extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        try {
            Connection conn = DBConnector.getConnection();
            productDAO = new ProductDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts(); // Updated method
        request.setAttribute("products", products);
        request.getRequestDispatcher("/product.jsp").forward(request, response); // Use forward instead of include
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
