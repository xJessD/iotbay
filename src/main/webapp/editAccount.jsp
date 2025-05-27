<%@ page import="model.User" %>
<html>
    <head>
        <title>IoTBay - Edit Account</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            .container {
                max-width: 600px;
                margin: 50px auto;
                background: white;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }
            
            .form-group {
                margin-bottom: 20px;
            }
            
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            
            .form-group input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            
            .form-actions {
                margin-top: 20px;
                text-align: right;
            }
            
            .button {
                display: inline-block;
                padding: 10px 20px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                margin-left: 10px;
                transition: background-color 0.3s ease;
                border: none;
                cursor: pointer;
            }
            
            .button:hover {
                background-color: #0056b3;
            }
            
            .button.secondary {
                background-color: #6c757d;
            }
            
            .button.secondary:hover {
                background-color: #545b62;
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
                    <h2>Error</h2>
                    <p>You must be logged in to edit your account. Please <a href="login.jsp">login</a> to continue.</p>
                <% } else { %>
                    <h2>Edit Account Details</h2>
                    <form action="UpdateAccountServlet" method="post">
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <input type="text" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <input type="text" id="lastName" name="lastName" value="<%= user.getLastName() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Phone:</label>
                            <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="password">New Password (leave blank to keep current):</label>
                            <input type="password" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm New Password:</label>
                            <input type="password" id="confirmPassword" name="confirmPassword">
                        </div>
                        <div class="form-actions">
                            <a href="account.jsp" class="button secondary">Cancel</a>
                            <button type="submit" class="button">Save Changes</button>
                        </div>
                    </form>
                <% } %>
            </section>
        </main>

        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>