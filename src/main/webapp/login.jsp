<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String errorMessage = request.getParameter("error");
    %>
    <head>
        <title>Log In</title>
        <link rel="stylesheet" href="css.css">
        
    </head>
    <body>
        <%@ include file="header.jsp" %> <!-- Site title and nav please do not remove -->
        <main>
            <section class="form-container">
                <h2>Log In</h2>
                <% if (errorMessage != null) { %>
                    <div class="error-message"><%= errorMessage %></div>
                <% } %>
                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn-login">Login</button>
                </form>
                <div class="form-footer">
                    <p><a href="register.jsp">Don't have an account? Register here</a></p>
                    <p><a href="forgotPassword.jsp">Forgot your password?</a></p>
                </div>
            </section>
        </main>
        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>