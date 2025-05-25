package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.dao.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1- Retrieve the current session
        HttpSession session = request.getSession();

        // 2- Create an instance of the Validator class
        Validator validator = new Validator();

        // 3- Validate the form inputs
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String isStaff = request.getParameter("isStaff"); // Changed from TOS to isStaff

        UserDAO manager = (UserDAO) session.getAttribute("manager");

        User user = null;
        
        // Validate firstName is not null or empty
        if (firstName == null || firstName.trim().isEmpty()) {
            session.setAttribute("errorMessage", "First name cannot be empty.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Validate lastName is not null or empty
        if (lastName == null || lastName.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Last name cannot be empty.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Validate password is not null or empty
        if (password == null || password.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Password cannot be empty.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!validator.validateEmail(email)) {
            session.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }
        
        // Validate phone number format
        if (!validator.validatePhoneNumber(phoneNumber)) {
            session.setAttribute("errorMessage", "Invalid phone number format.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        try {
            // 3- Check if email already exists
            if (manager.emailExists(email)) {
                session.setAttribute("errorMessage", "Email already exists.");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
            // 3- Create a new user object
            user = new User(firstName, lastName, email, password, phoneNumber);
            
            // Set account type based on checkbox
            if (isStaff != null && isStaff.equals("on")) {
                user.setAccountType("Staff");
            } else {
                user.setAccountType("Customer");
            }
            
            // 4- Add the user to the database
            manager.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                    user.getPhoneNumber(), user.getAccountType());
            // 5- Clear any error messages and set success message
            session.removeAttribute("errorMessage");
            // Store user details in session for confirmation page
            session.setAttribute("registeredUser", user);
            session.setAttribute("successMessage", "Registration successful. Please review your details below.");
            response.sendRedirect("/iotbay/registerConfirmation.jsp");
            return;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            // Set error message for database errors
            session.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}