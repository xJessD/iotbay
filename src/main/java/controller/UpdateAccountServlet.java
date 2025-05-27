package controller;

import model.User;
import model.dao.UserDAO;

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

@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        // Get the form data
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        
        // Validate the data
        Validator validator = new Validator();
        
        if (!validator.validateEmail(email)) {
            session.setAttribute("errorMessage", "Invalid email format.");
            response.sendRedirect("account.jsp");
            return;
        }
        
        if (!validator.validatePhoneNumber(phoneNumber)) {
            session.setAttribute("errorMessage", "Invalid phone number format.");
            response.sendRedirect("account.jsp");
            return;
        }
        
        // Update the user object
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        
        // Only update password if it's not empty
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }
        
        // Update the user in the database
        UserDAO userDAO = (UserDAO) session.getAttribute("manager");
        try {
            userDAO.updateUser(user);
            session.setAttribute("successMessage", "Your account has been successfully updated.");
        } catch (SQLException e) {
            session.setAttribute("errorMessage", "An error occurred while updating your account.");
            e.printStackTrace();
        }
        
        // Redirect back to the account page
        response.sendRedirect("account.jsp");
    }
}