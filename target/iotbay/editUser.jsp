<%@ page import="model.User, model.dao.UserDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <title>IoTBay - Edit User</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input, .form-group select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .form-actions {
                margin-top: 20px;
                display: flex;
                gap: 10px;
            }
            .form-actions button {
                padding: 10px 15px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .save-btn {
                background-color: #4CAF50;
                color: white;
            }
            .cancel-btn {
                background-color: #f44336;
                color: white;
            }
            .optional-field::after {
                content: " (optional)";
                font-weight: normal;
                font-style: italic;
                font-size: 0.9em;
                color: #666;
            }
        </style>
    </head>

    <body>
        <%@ include file="header.jsp" %>

        <main>
            <section class="container" style="padding: 20px; max-width: 600px; margin: 0 auto;">
                <% 
                    User currentUser = (User) session.getAttribute("user");
                    if (currentUser == null || !"Admin".equals(currentUser.getAccountType())) {
                        session.setAttribute("errorMessage", "You do not have permission to access this page.");
                        response.sendRedirect("index.jsp");
                        return;
                    }
                    
                    String userId = request.getParameter("id");
                    if (userId == null || userId.trim().isEmpty()) {
                        session.setAttribute("errorMessage", "Invalid user ID.");
                        response.sendRedirect("ManageUsersServlet");
                        return;
                    }
                    
                    UserDAO userDAO = (UserDAO) session.getAttribute("manager");
                    User userToEdit = null;
                    
                    try {
                        userToEdit = userDAO.getUserById(Integer.parseInt(userId));
                    } catch (Exception e) {
                        session.setAttribute("errorMessage", "Error retrieving user details.");
                        response.sendRedirect("ManageUsersServlet");
                        return;
                    }
                    
                    if (userToEdit == null) {
                        session.setAttribute("errorMessage", "User not found.");
                        response.sendRedirect("ManageUsersServlet");
                        return;
                    }
                %>
                
                <h1>Edit User</h1>
                
                <% if (session.getAttribute("errorMessage") != null) { %>
                    <div class="error-message"><%= session.getAttribute("errorMessage") %></div>
                    <% session.removeAttribute("errorMessage"); %>
                <% } %>
                
                <form action="UpdateUserServlet" method="post">
                    <input type="hidden" name="userId" value="<%= userToEdit.getUserID() %>">
                    
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" value="<%= userToEdit.getFirstName() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" value="<%= userToEdit.getLastName() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<%= userToEdit.getEmail() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="phoneNumber" class="optional-field">Phone Number</label>
                        <input type="text" id="phoneNumber" name="phoneNumber" value="<%= userToEdit.getPhoneNumber() != null ? userToEdit.getPhoneNumber() : "" %>">
                        <small style="color: #666; font-style: italic;">Format: 04XX XXX XXX or +61 X XXXX XXXX</small>
                    </div>
                    
                    <div class="form-group">
                        <label for="accountType">Account Type</label>
                        <select id="accountType" name="accountType" required>
                            <option value="Customer" <%= "Customer".equals(userToEdit.getAccountType()) ? "selected" : "" %>>Customer</option>
                            <option value="Staff" <%= "Staff".equals(userToEdit.getAccountType()) ? "selected" : "" %>>Staff</option>
                            <option value="Admin" <%= "Admin".equals(userToEdit.getAccountType()) ? "selected" : "" %>>Admin</option>
                        </select>
                    </div>
                    
                    <div class="form-actions">
                        <button type="submit" class="save-btn">Save Changes</button>
                        <a href="ManageUsersServlet" class="cancel-btn" style="text-decoration: none; display: inline-block; text-align: center; padding: 10px 15px;">Cancel</a>
                    </div>
                </form>
            </section>
        </main>

        <%@ include file="footer.jsp" %>
    </body>
</html>