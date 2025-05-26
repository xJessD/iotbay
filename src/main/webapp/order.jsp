<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Place Order</title>
    <link rel="stylesheet" type="text/css" href="css.css"> <!-- Make sure this file exists -->
</head>

<body>
<%@ include file="header.jsp" %>

<%
    // Simulated or existing session user
    User user = (User) session.getAttribute("user");
    if (user == null) {
        user = new User();
        user.setFirstName("TestUser");
        user.setCustomerID(1);
        session.setAttribute("user", user);
    }

    List<Product> products = (List<Product>) request.getAttribute("products");
%>

<div class="container">
    <h2>Place Your Order</h2>

    <p>Welcome, <strong><%= user.getFirstName() %></strong>!</p>

   

    <form action="order" method="post">
        <table border="1" cellpadding="8" cellspacing="0" style="width: 100%; text-align: center;">
            <tr style="background-color: #f2f2f2;">
                <th>Product</th>
                <th>Price</th>
                <th>Available Stock</th>
                <th>Quantity</th>
                <th>Special Requests</th>
            </tr>

            <% if (products != null && !products.isEmpty()) {
                for (Product p : products) {
            %>
            <tr>
                <td>
                    <input type="hidden" name="productID" value="<%= p.getProductID() %>" />
                    <%= p.getName() %>
                </td>
                <td>$<%= String.format("%.2f", p.getPrice()) %></td>
                <td><%= p.getStock() %></td>
                <td>
                    <input type="number" name="quantity" value="0" min="0" max="<%= p.getStock() %>" />
                </td>
                <td>
                    <input type="text" name="requests" placeholder="e.g. gift wrap" />
                </td>
            </tr>
            <% } } else { %>
            <tr>
                <td colspan="5">⚠️ No products available.</td>
            </tr>
            <% } %>
        </table>

        <br/>
        <input type="submit" value="Submit Order" class="form-button" />
    </form>

    <div style="margin-top: 1em;">
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: green;"><%= request.getAttribute("message") %></p>
        <% } else if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
