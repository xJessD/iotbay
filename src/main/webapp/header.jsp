<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<header class="site-header">
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

<style>
    .site-header {
        background-color: #333;
        color: white;
        padding: 10px 0;
        width: 100%;
    }
    
    .header-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 20px;
        width: 100%;
    }
    
    .logo {
        flex: 0 0 auto;
    }
    
    .logo a {
        color: white;
        text-decoration: none;
        font-size: 24px;
        font-weight: bold;
    }
    
    .search-box {
        flex: 0 0 auto;
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        width: 300px;
    }
    
    .search-box form {
        display: flex;
    }
    
    .search-box input[type="text"] {
        flex-grow: 1;
        padding: 8px;
        border: none;
        border-radius: 4px 0 0 4px;
    }
    
    .search-box button {
        background-color: #ff6c44;
        color: white;
        border: none;
        padding: 8px 15px;
        border-radius: 0 4px 4px 0;
        cursor: pointer;
    }
    
    .main-nav {
        flex: 0 0 auto;
        margin-left: auto;
    }
    
    .main-nav ul {
        display: flex;
        list-style: none;
        margin: 0;
        padding: 0;
    }
    
    .main-nav li {
        margin-left: 20px;
    }
    
    .main-nav a {
        color: white;
        text-decoration: none;
    }
    
    .main-nav a:hover {
        color: #ff6c44;
    }
    
    .main-nav span {
        color: #ff6c44;
    }
    
    .logout-button {
        background-color: #ff6c44;
        padding: 5px 10px;
        border-radius: 4px;
    }
    
    .logout-button:hover {
        background-color: #e55a33;
        color: white !important;
    }
<<<<<<< HEAD
</style>
=======
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
            <li><a href="/webapp/login.jsp">Login</a></li>
            <li><a href="/webapp/register.jsp">Register</a></li>
        </ul>
    </div>
</nav>
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
</style>
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
