<%@ page import="model.User" %>
<%@ page session="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<div class="container">
    <h2>Place Your Order</h2>

    <p>Welcome, <strong><%= user.getFirstName() %></strong>!</p>

    <!-- Navigation Links -->
    <p>
        <a href="index.jsp">🏠 Home</a> |
        <a href="order?action=view">📦 My Orders</a> |
        <a href="logout.jsp">🚪 Logout</a>
    </p>

    <form action="order" method="post">
        <table border="1" cellpadding="8" cellspacing="0">
            <tr>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Special Requests</th>
            </tr>

            <!-- Example rows – replace with loop for real data -->
            <tr>
                <td><input type="text" name="productID" value="101" readonly></td>
                <td><input type="number" name="quantity" value="1" min="1" required></td>
                <td><input type="text" name="requests" placeholder="e.g. gift wrap" /></td>
            </tr>
            <tr>
                <td><input type="text" name="productID" value="102" readonly></td>
                <td><input type="number" name="quantity" value="2" min="1" required></td>
                <td><input type="text" name="requests" /></td>
            </tr>
        </table>

        <br/>
        <input type="submit" value="Submit Order" />
    </form>

    <!-- Feedback messages -->
    <div style="margin-top: 1em;">
        <% if (request.getAttribute("message") != null) { %>
            <p style="color: green;"><%= request.getAttribute("message") %></p>
        <% } else if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
    </div>
</div>

<%@ include file="footer.jsp" %>
