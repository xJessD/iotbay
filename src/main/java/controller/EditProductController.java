package main.java.controller;

import main.java.model.ProductCatalog;
import main.java.model.dao.DBConnector;
import main.java.model.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles listing and updating products.
 */
@WebServlet("/products/edit")
public class EditProductController extends HttpServlet {

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
            throws ServletException, IOException {

        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/productManage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            int productID = Integer.parseInt(request.getParameter("ProductID"));
            String name = request.getParameter("Name");
            int quantity = Integer.parseInt(request.getParameter("Stock"));
            double price = Double.parseDouble(request.getParameter("Price"));
            String description = request.getParameter("Description");
            String imageUrl = request.getParameter("imageUrl");
            boolean favourited = request.getParameter("favourited") != null;

            Product updatedProduct = new Product(
                    productID,
                    name,
                    imageUrl != null && !imageUrl.isEmpty() ? imageUrl : "images/default.jpg",
                    description,
                    price,
                    quantity,
                    favourited
            );

            productDAO.update(updatedProduct);
            response.sendRedirect(request.getContextPath() + "/products/edit");

        } catch (Exception e) {
            request.setAttribute("error", "Failed to update product: " + e.getMessage());
            request.getRequestDispatcher("/productManage.jsp").forward(request, response);
        }
    }
}
