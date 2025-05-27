<%@ page import="java.util.List" %>
<%@ page import="model.AccessLog" %>
<%@ page import="model.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Access Logs</title>
    <link href="css.css" rel="stylesheet" type="text/css">
    <style>
        .logs-container {
            margin: 20px 0;
        }
        .log-entry {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 15px;
            position: relative;
        }
        .log-entry-header {
            font-weight: bold;
            margin-bottom: 10px;
            color: #333;
        }
        .log-entry-content {
            display: flex;
            justify-content: space-between;
        }
        @media (max-width: 600px) {
            .log-entry-content {
                flex-direction: column;
            }
        }
        .search-form {
            background-color: #f5f5f5;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .search-form label {
            margin-right: 10px;
        }
        .search-form input[type="date"] {
            padding: 5px;
            border: 1px solid #ddd;
            border-radius: 3px;
            margin-right: 15px;
        }
        .search-form input[type="submit"] {
            padding: 5px 15px;
            background-color: #F96E46;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .search-form input[type="submit"]:hover {
            background-color: #e85d35;
        }
        .success-message {
            color: green;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            padding: 10px;
            margin-bottom: 15px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
        }
        .error-message {
            color: red;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            margin-bottom: 15px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
        }
        .delete-btn {
            position: absolute;
            top: 10px;
            right: 10px;
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 3px;
            padding: 5px 10px;
            cursor: pointer;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .admin-controls {
            margin-top: 20px;
            text-align: right;
        }
        .delete-all-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            border-radius: 3px;
            padding: 8px 15px;
            cursor: pointer;
            font-weight: bold;
        }
        .delete-all-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <main>
        <h1>Access Logs</h1>
        <% 
            // Get success and error messages from session
            String successMessage = (String) session.getAttribute("successMessage");
            String errorMessage = (String) session.getAttribute("errorMessage");
            
            // Display messages if they exist
            if (successMessage != null) {
                // Clear the message after displaying it
                session.removeAttribute("successMessage");
        %>
                <div class="success-message"><%= successMessage %></div>
        <%
            }
            
            if (errorMessage != null) {
                // Clear the message after displaying it
                session.removeAttribute("errorMessage");
        %>
                <div class="error-message"><%= errorMessage %></div>
        <%
            }
            
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
            } else {
                List<AccessLog> logs = (List<AccessLog>) request.getAttribute("logs");
                // Define the date formatter with the desired pattern
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                
                // Get the current search parameters to preserve them in case of error
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");
                
                // Check if user is admin
                boolean isAdmin = "Admin".equals(user.getAccountType());
        %>
                <div class="search-form">
                    <form action="accessLogs" method="get">
                        <label for="startDate">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" value="<%= startDate != null ? startDate : "" %>">
                        
                        <label for="endDate">End Date:</label>
                        <input type="date" id="endDate" name="endDate" value="<%= endDate != null ? endDate : "" %>">
                        
                        <input type="submit" value="Search">
                    </form>
                </div>
                <%
                if (logs != null && !logs.isEmpty()) {
                    // If admin, show delete all button
                    if (isAdmin) {
                %>
                    <div class="admin-controls">
                        <form action="DeleteLogServlet" method="post" onsubmit="return confirm('Are you sure you want to delete all logs? This action cannot be undone.');">
                            <input type="hidden" name="action" value="deleteAll">
                            <input type="hidden" name="userId" value="<%= user.getUserID() %>">
                            <button type="submit" class="delete-all-btn">Delete All Logs</button>
                        </form>
                    </div>
                <%
                    }
                %>
                <div class="logs-container">
                    <% for (AccessLog log : logs) { %>
                        <div class="log-entry">
                            <div class="log-entry-header">Access Log Entry</div>
                            <% if (isAdmin) { %>
                                <form action="DeleteLogServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this log?');">
                                    <input type="hidden" name="logId" value="<%= log.getId() %>">
                                    <input type="hidden" name="action" value="deleteOne">
                                    <button type="submit" class="delete-btn">Delete</button>
                                </form>
                            <% } %>
                            <div class="log-entry-content">
                                <div>
                                    <strong>Login:</strong> <%= log.getLoginDateTime() != null ? log.getLoginDateTime().format(formatter) : "N/A" %>
                                </div>
                                <div>
                                    <strong>Logout:</strong> <%= log.getLogoutDateTime() != null ? log.getLogoutDateTime().format(formatter) : "N/A" %>
                                </div>
                            </div>
                        </div>
                    <% } %>
                </div>
            <% 
                } else {
            %>
                <p>No access logs found.</p>
            <%
                }
            }
            %>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>