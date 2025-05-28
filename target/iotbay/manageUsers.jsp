<%@ page import="model.User, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IoTBay - Manage Users</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            .user-table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }
            .user-table th, .user-table td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: left;
            }
            .user-table th {
                background-color: #f2f2f2;
                font-weight: bold;
            }
            .user-table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            .user-table tr:hover {
                background-color: #f1f1f1;
            }
            .action-buttons {
                display: flex;
                gap: 5px;
            }
            .action-buttons button, .action-buttons a {
                padding: 5px 10px;
                text-decoration: none;
                border-radius: 3px;
                font-size: 14px;
                cursor: pointer;
            }
            .edit-btn {
                background-color: #4CAF50;
                color: white;
                border: none;
            }
            .delete-btn {
                background-color: #f44336;
                color: white;
                border: none;
            }
            .search-container {
                margin-bottom: 20px;
                display: flex;
                gap: 10px;
            }
            .search-container input {
                padding: 8px;
                width: 300px;
            }
            .search-container button {
                padding: 8px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>

    <body>
        <%@ include file="header.jsp" %>

        <main>
            <section class="container" style="padding: 20px;">
                <% 
                    User currentUser = (User) session.getAttribute("user");
                    if (currentUser == null || !"Admin".equals(currentUser.getAccountType())) {
                        session.setAttribute("errorMessage", "You do not have permission to access this page.");
                        response.sendRedirect("index.jsp");
                        return;
                    }
                    
                    List<User> users = (List<User>) request.getAttribute("users");
                %>
                
                <h1>Manage Users</h1>
                
                <% if (session.getAttribute("successMessage") != null) { %>
                    <div class="success-message"><%= session.getAttribute("successMessage") %></div>
                    <% session.removeAttribute("successMessage"); %>
                <% } %>
                
                <% if (session.getAttribute("errorMessage") != null) { %>
                    <div class="error-message"><%= session.getAttribute("errorMessage") %></div>
                    <% session.removeAttribute("errorMessage"); %>
                <% } %>
                
                <div class="search-container">
                    <form action="ManageUsersServlet" method="get">
                        <input type="text" name="searchTerm" placeholder="Search by name or email..." 
                               value="<%= request.getParameter("searchTerm") != null ? request.getParameter("searchTerm") : "" %>">
                        <button type="submit">Search</button>
                    </form>
                </div>
                
                <% if (users != null && !users.isEmpty()) { %>
                    <table class="user-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Account Type</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (User user : users) { %>
                                <tr>
                                    <td><%= user.getUserID() %></td>
                                    <td><%= user.getFirstName() %> <%= user.getLastName() %></td>
                                    <td><%= user.getEmail() %></td>
                                    <td><%= user.getPhoneNumber() %></td>
                                    <td><%= user.getAccountType() %></td>
                                    <td class="action-buttons">
                                        <a href="editUser.jsp?id=<%= user.getUserID() %>" class="edit-btn">Edit</a>
                                        <form action="DeleteUserServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this user?');">
                                            <input type="hidden" name="userId" value="<%= user.getUserID() %>">
                                            <button type="submit" class="delete-btn">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <p>No users found.</p>
                <% } %>
            </section>
        </main>

        <%@ include file="footer.jsp" %>
    </body>
</html>