package controller;

import model.*;
import model.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            orderDAO = new OrderDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Assuming user is logged in and stored in session
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int customerID = user.getUserID();  

        // Get form data
        String[] productIDs = request.getParameterValues("productID");
        String[] quantities = request.getParameterValues("quantity");
        String[] requests = request.getParameterValues("requests");

        Date now = new Date();
        Order order = new Order();
        order.setCustomerID(customerID);
        order.setOrderDate(now);
        order.setCreatedDate(now);
        order.setUpdatedDate(now);
        order.setOrderStatus("pending");

        List<OrderLine> orderLines = new ArrayList<>();

        for (int i = 0; i < productIDs.length; i++) {
            int productID = Integer.parseInt(productIDs[i]);
            int quantity = Integer.parseInt(quantities[i]);
            String req = (requests != null && requests.length > i) ? requests[i] : "";

            OrderLine line = new OrderLine();
            line.setProductID(productID);
            line.setQuantity(quantity);
            line.setRequests(req);
            line.setCreatedDate(now);
            line.setUpdatedDate(now);

            orderLines.add(line);
        }

        try {
            orderDAO.createOrder(order, orderLines);
            request.setAttribute("message", "✅ Order placed successfully!");
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Order failed to process.");
            request.getRequestDispatcher("order.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int customerID = user.getUserID(); // Or getCustomerID()

        try {
            if ("view".equals(action)) {
                List<Order> orders = orderDAO.getOrdersByCustomer(customerID);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orderHistory.jsp").forward(request, response);

            } else if ("cancel".equals(action)) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                orderDAO.cancelOrder(orderID);
                response.sendRedirect("order?action=view");

            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
