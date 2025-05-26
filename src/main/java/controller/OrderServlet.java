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
    private ProductDAO productDAO;

    @Override
    public void init() {
        try {
            DBConnector connector = new DBConnector();
            Connection conn = connector.openConnection();
            orderDAO = new OrderDAO(conn);
            productDAO = new ProductDAO(conn);
            System.out.println("✅ DAOs initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            System.out.println("👤 No user in session. Creating dummy user.");
            user = new User();
            user.setCustomerID(1);
            user.setFirstName("TestUser");
            session.setAttribute("user", user);
        }

        int customerID = user.getCustomerID();

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

        try {
            for (int i = 0; i < productIDs.length; i++) {
                int productID = Integer.parseInt(productIDs[i]);
                int quantity = Integer.parseInt(quantities[i]);
                String req = (requests != null && requests.length > i) ? requests[i] : "";

                if (quantity > 0) {
                    OrderLine line = new OrderLine();
                    line.setProductID(productID);
                    line.setQuantity(quantity);
                    line.setRequests(req);
                    line.setCreatedDate(now);
                    line.setUpdatedDate(now);
                    orderLines.add(line);
                }
            }

            if (!orderLines.isEmpty()) {
                orderDAO.createOrder(order, orderLines);
                request.setAttribute("message", "✅ Order placed successfully!");
            } else {
                request.setAttribute("error", "❌ Please select at least one product.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "❌ Failed to process order.");
        }

        try {
            List<Product> products = productDAO.getAllProducts();
            System.out.println("📦 POST: products loaded = " + products.size());
            request.setAttribute("products", products);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            System.out.println("👤 No user in session. Creating dummy user.");
            user = new User();
            user.setCustomerID(1);
            user.setFirstName("TestUser");
            session.setAttribute("user", user);
        }

        int customerID = user.getCustomerID();
        String action = request.getParameter("action");
        System.out.println("🔍 GET action: " + action);

        try {
            if ("view".equals(action)) {
                List<Order> orders = orderDAO.getAllOrders(); // 👈 change to get all orders
                System.out.println("📋 Loaded " + orders.size() + " orders.");
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("orderHistory.jsp").forward(request, response);

            } else if ("cancel".equals(action)) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                orderDAO.cancelOrder(orderID);
                System.out.println("❌ Cancelled order ID " + orderID);
                response.sendRedirect("order?action=view");

            } else {
                List<Product> products = productDAO.getAllProducts();
                System.out.println("🛒 Loaded " + products.size() + " products.");
                request.setAttribute("products", products);
                request.getRequestDispatcher("order.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("🚨 SQL Error: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
}
