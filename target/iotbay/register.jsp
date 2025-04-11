<html>
    <%
        String errorMessage = request.getParameter("error");
    %>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="css.css">
        <style>
            /* Form Styling */
            a {
                color: #ff8a8a;
            }
            .register-form-container {
                background-color: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                max-width: 500px;
                margin: 50px auto;
            }
            .form-group {
                margin-bottom: 1.5rem;
            }
            .form-group label {
                display: block;
                margin-bottom: 0.5rem;
                font-weight: 500;
            }
            .form-group input {
                width: 100%;
                padding: 0.8rem;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 1rem;
            }
            .checkbox-group {
                display: flex;
                align-items: flex-start;
                margin-bottom: 1.5rem;
            }
            .checkbox-group input {
                margin-top: 0.2rem;
                margin-left: 0.8rem;
            }
            .form-button {
                background-color: #F96E46;
                color: white;
                border: none;
                padding: 0.8rem 1.5rem;
                font-size: 1rem;
                border-radius: 4px;
                width: 100%;
                margin-bottom: 1rem;
            }
            .form-button:hover {
                background-color: #DDD;
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
        <%@ include file="header.jsp" %><!-- Site title and nav please do not remove -->
       
        <main><!-- Please put all content inside the main tags -->
            <section class="register-form-container">
                <h2>Register</h2>
                
                <% if (errorMessage != null) { %>
                    <div class="error-message"><%= errorMessage %></div>
                <% } %>
                
                <form action="RegisterServlet" method="post">
                    <div class="form-group label">
                        <label for="fname">First Name: </label>
                        <input type="text" id="fname" name="fname" required/>
                    </div>
                   
                    <div class="form-group label">
                        <label for="lname">Last Name: </label>
                        <input type="text" id="lname" name="lname" required/>
                    </div>
                    <div class="form-group label">
                        <label for="email">Email: </label>
                        <input type="text" id="email" name="email" required>
                    </div>
                    <div class="form-group label">
                        <label for="password">Password: </label>
                        <input type="password" id="password" name="password" required/>
                    </div>
                   
                    <div class="checkbox-group">
                        <label for="TOS">TOS: </label>
                        <input type="checkbox" id="TOS" name="TOS" required/>
                    </div>
                    <button type="submit" class="form-button">Register</button>
                    <div class="form-group label">Already have an account? <a href="login.jsp">Log in</a>.</div>
                </form>
            </section>
        </main>
       
        <%@ include file="footer.jsp" %><!-- Site footer please do not remove -->
    </body>
</html>