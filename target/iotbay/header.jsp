<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<header class="site-header">
    <link rel="stylesheet" href="css.css">
    <div class="header-container">
        <div class="logo">
            <a href="index.jsp">IoTBay</a>
        </div>
       
        <div class="search-box">
            <form action="search" method="get">
                <input type="text" name="query" placeholder="Search...">
                <button type="submit">Search</button>
            </form>
        </div>
       
        <nav class="main-nav">
            <ul>
                <li><a href="products.jsp">Products</a></li>
                <%
                    // Check if user is logged in
                    User headerUser = (User)session.getAttribute("user");
                    if (headerUser == null) {
                        // User is not logged in, show login and register links
                %>
                    <li><a href="register.jsp">Register</a></li>
                <% } else {
                    // User is logged in, show logout button and username
                %>
                    <li><span>Welcome, <%= headerUser.getFname() %></span></li>
                    <li><a href="LogoutServlet" class="logout-button">Logout</a></li>
                <% } %>
            </ul>
        </nav>
    </div>
</header>

