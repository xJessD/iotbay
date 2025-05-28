<%@ page import="model.User" %>
<html>
    <head>
        <title>IoTBay - Account</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            section a { 
                text-decoration: none;
                color: #F96E46;
                transition: color 0.3s ease;
            }

            .container {
                max-width: 800px;
                margin: 50px auto;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }
            
            .account-details {
                display: grid;
                grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
                gap: 20px;
                margin-bottom: 30px;
            }

            .detail-item {
                background-color: #f8f9fa;
                border-radius: 5px;
                padding: 15px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .detail-label {
                font-weight: bold;
                display: block;
                margin-bottom: 5px;
                color: #495057;
            }

            .detail-value {
                color: #212529;
            }

            .account-actions, .admin-actions {
                margin-top: 20px;
            }

            .button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-right: 10px;
                transition: background-color 0.3s ease;
            }

            .button:hover {
                background-color: #0056b3;
            }

            .button.danger {
                background-color: #dc3545;
            }
            
            .button.danger:hover {
                background-color: #c82333;
            }
            
            .success-message {
                background-color: #d4edda;
                color: #155724;
                padding: 10px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
        </style>
    </head>

    <body>
        <%@ include file="header.jsp" %> <!-- Site title and nav please do not remove -->

        <main> <!-- Please put all content inside the main tags -->
            <section class="container">
                <%
                    User user = (User) session.getAttribute("user");
                    if (user == null) {
                %>
                    <h2>Welcome Guest</h2>
                    <p>Please <a href="login.jsp">login</a> or <a href="register.jsp">register</a> to access your account.</p>
                <% } else { %>
                    <h2>Welcome <%= user.getFirstName() %>!</h2>

                    <h3>Account Details</h3>
                    <div class="account-details">
                        <div class="detail-item">
                            <span class="detail-label">First Name:</span>
                            <span class="detail-value"><%= user.getFirstName() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Last Name:</span>
                            <span class="detail-value"><%= user.getLastName() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Email:</span>
                            <span class="detail-value"><%= user.getEmail() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Phone:</span>
                            <span class="detail-value"><%= user.getPhoneNumber() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Account Type:</span>
                            <span class="detail-value"><%= user.getAccountType() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Created Date:</span>
                            <span class="detail-value"><%= user.getCreatedDate() %></span>
                        </div>
                        <div class="detail-item">
                            <span class="detail-label">Last Modified:</span>
                            <span class="detail-value"><%= user.getUpdatedDate() %></span>
                        </div>
                    </div>

                    <div class="account-actions">
                        <a href="editAccount.jsp" class="button">Edit Account Details</a>
                        <a href="accessLogs" class="button">View Access Logs</a>
                        <a href="ShipmentServlet" class="button">My Shipments</a>
                        <a href="order?action=view" class="button">My Orders</a>
                        <% if ("Staff".equals(user.getAccountType()) || "Customer".equals(user.getAccountType())) { %>
                            <a href="deleteAccount.jsp" class="button danger">Delete Account</a>
                        <% } %>
                    </div>

                    <% if ("Admin".equals(user.getAccountType()) || "Staff".equals(user.getAccountType())) { %>
                        <div class="admin-actions">
                            <a href="ManageUsersServlet" class="button">Manage Users</a>
                        </div>
                    <% } %>
                <% } %>
            </section>
        </main>

        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>
