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
            display: flex;
            flex-direction: column;
            gap: 1rem;
            margin-top: 2rem;
        }
        .log-entry {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 1rem;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        .log-entry-header {
            font-weight: bold;
            margin-bottom: 0.5rem;
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
            margin-bottom: 2rem;
        }
        .search-form input[type="date"] {
            padding: 0.5rem;
            margin-right: 1rem;
        }
        .search-form input[type="submit"] {
            padding: 0.5rem 1rem;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
        }
        .success-message {
            color: green;
            background-color: #e8f5e9;
            border: 1px solid #c8e6c9;
            padding: 10px;
            margin-bottom: 15px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
        }
        .error-message {
            color: red;
            background-color: #ffebee;
            border: 1px solid #ffcdd2;
            padding: 10px;
            margin-bottom: 15px;
            text-align: center;
            font-weight: bold;
            border-radius: 5px;
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
        %>
                <div class="search-form">
                    <form action="accessLogs" method="get">
                        <label for="startDate">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" value="${param.startDate}">
                        
                        <label for="endDate">End Date:</label>
                        <input type="date" id="endDate" name="endDate" value="${param.endDate}">
                        
                        <input type="submit" value="Search">
                    </form>
                </div>
                <%
                if (logs != null && !logs.isEmpty()) {
        %>
            <div class="logs-container">
                <% for (AccessLog log : logs) { %>
                    <div class="log-entry">
                        <div class="log-entry-header">Access Log Entry</div>
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