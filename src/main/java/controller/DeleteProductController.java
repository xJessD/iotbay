package main.java.controller;

import main.java.model.dao.DBConnector;
import main.java.model.dao.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Controller to handle deletion of a product.
 */
@WebServlet("/products/delete")
public class DeleteProductController extends HttpServlet {

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
            int productId = Integer.parseInt(request.getParameter("ProductID"));
            productDAO.delete(productId);
            response.sendRedirect(request.getContextPath() + "/productManage.jsp");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid product ID.");
            request.getRequestDispatcher("/productManage.jsp").forward(request, response);
        }
    }
}
