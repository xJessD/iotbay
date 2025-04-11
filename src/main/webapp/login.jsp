<<<<<<< HEAD
<<<<<<< HEAD
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
=======
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
<html>
    <%
        String errorMessage = request.getParameter("error");
    %>
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
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
<<<<<<< HEAD
=======

    <head>
        <title>Log In</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            .form-container {
                max-width: 500px;
                margin: 50px auto;
                padding: 40px;
                background: white;
                border-radius: 10px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            }

            .form-container h2 {
                text-align: center;
                margin-bottom: 30px;
                font-size: 24px;
            }

            .form-group {
                margin-bottom: 20px;
                text-align: left;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
            }

            .form-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
            }

            .btn-login {
                width: 100%;
                background-color: #d66b43;
                color: white;
                padding: 12px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-top: 10px;
            }

            .btn-login:hover {
                background-color: #c65c38;
            }

            .form-footer {
                margin-top: 20px;
                font-size: 14px;
                text-align: center;
            }

            .form-footer a {
                color: #0077cc;
                text-decoration: none;
            }

            .form-footer a:hover {
                text-decoration: underline;
            }

            .error-message {
                color: red;
                margin-bottom: 15px;
                text-align: center;
                font-weight: bold;
            }
        </style>
    </head>

    <body>
        <%@ include file="header.jsp" %> <!-- Site title and nav please do not remove -->

        <main>
            <section class="form-container">
                <h2>Log In</h2>

                <% if (errorMessage != null) { %>
                    <div class="error-message"><%= errorMessage %></div>
                <% } %>

>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" id="email" name="email" required>
                    </div>
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
<<<<<<< HEAD
<<<<<<< HEAD
                    <button type="submit" class="btn-login">Login</button>
                </form>
=======

                    <button type="submit" class="btn-login">Login</button>
                </form>

>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
                    <button type="submit" class="btn-login">Login</button>
                </form>
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
                <div class="form-footer">
                    <p><a href="register.jsp">Don't have an account? Register here</a></p>
                    <p><a href="forgotPassword.jsp">Forgot your password?</a></p>
                </div>
            </section>
        </main>
<<<<<<< HEAD
<<<<<<< HEAD
        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>
=======

        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>
>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
=======
        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>
>>>>>>> fc2a438f0fdd803075a5b2a56bffcab1ecbbdaec
