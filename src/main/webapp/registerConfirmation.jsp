<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title>IoTBay - Registration Confirmation</title>
        <link href="css.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <%@ include file="header.jsp" %> <!-- Site title and nav please do not remove -->

        <main> <!-- Please put all content inside the main tags -->
            <section class="container" style="text-align: center; padding: 50px;">
                <% 
                    User registeredUser = (User) session.getAttribute("registeredUser");
                    if (registeredUser == null) {
                        response.sendRedirect("register.jsp");
                        return;
                    }
                %>
                
                <% if (session.getAttribute("successMessage") != null) { %>
                    <div class="success-message"><%= session.getAttribute("successMessage") %></div>
                    <% session.removeAttribute("successMessage"); %>
                <% } %>

                <h1>Registration Confirmation</h1>
                <div class="confirmation-details" style="margin: 30px auto; max-width: 500px; text-align: left; padding: 20px; border: 1px solid #ddd; border-radius: 5px; background-color: #f9f9f9;">
                    <p><strong>Name:</strong> <%= registeredUser.getFirstName() %> <%= registeredUser.getLastName() %></p>
                    <p><strong>Email:</strong> <%= registeredUser.getEmail() %></p>
                    <p><strong>Phone Number:</strong> <%= registeredUser.getPhoneNumber() %></p>
                    <p><strong>Account Type:</strong> <%= registeredUser.getAccountType() %></p>
                </div>

                <p>Your account has been successfully created. You can now log in to access your account.</p>
                
                <div style="margin-top: 30px;">
                    <a href="login.jsp">
                        <button style="padding: 12px 24px; font-size: 16px;">Proceed to Login</button>
                    </a>
                </div>
                
                <% 
                    // Remove the registered user from session after displaying
                    session.removeAttribute("registeredUser");
                %>
            </section>
        </main>

        <%@ include file="footer.jsp" %> <!-- Site footer please do not remove -->
    </body>
</html>