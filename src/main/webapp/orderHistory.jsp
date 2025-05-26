<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>
    <link rel="stylesheet" type="text/css" href="css.css">
</head>

<body>
<%@ include file="header.jsp" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        user = new User();
        user.setFirstName("TestUser");
        session.setAttribute("user", user);
    }

    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<div class="container">
    <h2>My Orders</h2>

    <p style="text-align: center;">Welcome, <strong><%= user.getFirstName() %></strong>!</p>

    

    <% if (orders == null || orders.isEmpty()) { %>
        <p style="text-align: center;">No orders found.</p>
    <% } else { %>
        <div style="overflow-x:auto;">
            <table style="width: 100%; border-collapse: collapse; background-color: white; text-align: center;">
                <thead style="background-color: #f2f2f2;">
                    <tr>
                        <th style="padding: 10px; border: 1px solid #ddd;">Order ID</th>
                        <th style="padding: 10px; border: 1px solid #ddd;">Status</th>
                        <th style="padding: 10px; border: 1px solid #ddd;">Order Date</th>
                        <th style="padding: 10px; border: 1px solid #ddd;">Created</th>
                        <th style="padding: 10px; border: 1px solid #ddd;">Updated</th>
                        <th style="padding: 10px; border: 1px solid #ddd;">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Order order : orders) { %>
                        <tr>
                            <td style="padding: 10px; border: 1px solid #ddd;"><%= order.getOrderID() %></td>
                            <td style="padding: 10px; border: 1px solid #ddd;"><%= order.getOrderStatus() %></td>
                            <td style="padding: 10px; border: 1px solid #ddd;"><%= order.getOrderDate() %></td>
                            <td style="padding: 10px; border: 1px solid #ddd;"><%= order.getCreatedDate() %></td>
                            <td style="padding: 10px; border: 1px solid #ddd;"><%= order.getUpdatedDate() %></td>
                            <td style="padding: 10px; border: 1px solid #ddd;">
                                <% if ("pending".equalsIgnoreCase(order.getOrderStatus())) { %>
                                    <a href="order?action=cancel&orderID=<%= order.getOrderID() %>" style="color: red;">❌ Cancel</a>
                                <% } else { %>
                                    <span style="color: gray;">(locked)</span>
                                <% } %>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    <% } %>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
