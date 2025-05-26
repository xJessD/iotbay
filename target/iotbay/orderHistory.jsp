<%@ page import="model.User, model.Order, java.util.List" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<div class="container">
    <h2>My Orders</h2>

    <p>Welcome, <strong><%= user.getFirstName() %></strong>!</p>

    <!-- Navigation -->
    <p>
        <a href="index.jsp">🏠 Home</a> |
        <a href="order.jsp">🛒 Place New Order</a> |
        <a href="logout.jsp">🚪 Logout</a>
    </p>

    <% if (orders == null || orders.isEmpty()) { %>
        <p>No orders found.</p>
    <% } else { %>
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Order ID</th>
                <th>Status</th>
                <th>Order Date</th>
                <th>Created</th>
                <th>Updated</th>
                <th>Actions</th>
            </tr>
            <% for (Order order : orders) { %>
                <tr>
                    <td><%= order.getOrderID() %></td>
                    <td><%= order.getOrderStatus() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getCreatedDate() %></td>
                    <td><%= order.getUpdatedDate() %></td>
                    <td>
                        <% if ("pending".equalsIgnoreCase(order.getOrderStatus())) { %>
                            <a href="order?action=cancel&orderID=<%= order.getOrderID() %>">❌ Cancel</a>
                        <% } else { %>
                            <span style="color: gray;">(locked)</span>
                        <% } %>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } %>
</div>

<%@ include file="footer.jsp" %>
