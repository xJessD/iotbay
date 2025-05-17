<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<nav>
    <div class="nav-left">
        <h1 class="site-title">IoTBay</h1>
    </div>

    <div class="search-bar">
        <input type="text" placeholder="Search...">
        <button type="submit">Search</button>
    </div>

    <div class="nav-right">
        <ul class="nav-links">
            <li><a href="">Products</a></li>
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
                    <li><span>Welcome, <%= headerUser.getFirstName() %></span></li>
                    <li><a href="LogoutServlet" class="logout-button">Logout</a></li>
                <% } %>
        </ul>
    </div>
</nav>