<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IoTBay - Delete Account</title>
    <link href="css.css" rel="stylesheet" type="text/css">
    <style>
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        
        .warning {
            color: #dc3545;
            font-weight: bold;
            margin: 20px 0;
        }
        
        .form-actions {
            margin-top: 30px;
            display: flex;
            justify-content: center;
            gap: 20px;
        }
        
        .button {
            display: inline-block;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            border: none;
            cursor: pointer;
            font-weight: bold;
        }
        
        .button.primary {
            background-color: #007bff;
            color: white;
        }
        
        .button.primary:hover {
            background-color: #0056b3;
        }
        
        .button.danger {
            background-color: #dc3545;
            color: white;
        }
        
        .button.danger:hover {
            background-color: #c82333;
        }
        
        .error-message {
            color: #dc3545;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
    <%@ include file="header.jsp" %>

    <main>
        <section class="container">
            <%
                User user = (User) session.getAttribute("user");
                if (user == null) {
            %>
                <h2>Error</h2>
                <p>You must be logged in to delete your account. Please <a href="login.jsp">login</a> to continue.</p>
            <% } else { %>
                <h2>Delete Account</h2>
                
                <% if (session.getAttribute("errorMessage") != null) { %>
                    <p class="error-message"><%= session.getAttribute("errorMessage") %></p>
                    <% session.removeAttribute("errorMessage"); %>
                <% } %>
                
                <p>Are you sure you want to delete your account?</p>
                <p class="warning">This action cannot be undone. All your data will be permanently deleted.</p>
                
                <div class="form-actions">
                    <a href="account.jsp" class="button primary">Cancel</a>
                    <form action="DeleteAccountServlet" method="post">
                        <button type="submit" class="button danger">Delete My Account</button>
                    </form>
                </div>
            <% } %>
        </section>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>