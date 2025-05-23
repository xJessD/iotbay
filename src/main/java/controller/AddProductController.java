package main.java.controller;

import main.java.model.ProductCatalog;
import main.java.model.dao.DBConnector;
import main.java.model.dao.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Handles product addition requests.
 */
@WebServlet("/products/add")
public class AddProductController extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        try {
            productDAO = new ProductDAO(DBConnector.getConnection());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

    try {
        String name = request.getParameter("Name");
        int quantity = Integer.parseInt(request.getParameter("Stock"));
        double price = Double.parseDouble(request.getParameter("Price"));
        String description = request.getParameter("Description");
        String imageUrl = request.getParameter("imageUrl"); // may be blank
        boolean favourited = request.getParameter("favourited") != null;

        Product product = new Product(
                -1, // temporary ID
                name,
                imageUrl, // Product will handle default
                description,
                price,
                quantity,
                favourited
        );

        productDAO.add(product);
        response.sendRedirect(request.getContextPath() + "/productManage.jsp");

    } catch (Exception e) {
        request.setAttribute("error", "Failed to add product: " + e.getMessage());
        request.getRequestDispatcher("/productManage.jsp").forward(request, response);
        }
    }
}
