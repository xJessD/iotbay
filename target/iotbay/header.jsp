<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<nav>
    <div class="nav-left">
        <div class="site-title"><a href="index.jsp" style="text-decoration:none;">IoTBay</a></div>
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
                %>  <li><a href="login.jsp">Login</a></li>
                    <li><a href="register.jsp">Register</a></li>
                <% } else {
                    // User is logged in, show logout button and username
                %>
                    <li><span><a href="account.jsp">Welcome, <%= headerUser.getFirstName() %></a></span></li>
                    <li><a href="LogoutServlet" class="logout-button">Logout</a></li>
                <% } %>
        </ul>
    </div>
</nav>