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

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // Check if user is logged in and is an admin
        if (currentUser == null || !"Admin".equals(currentUser.getAccountType())) {
            session.setAttribute("errorMessage", "You do not have permission to perform this action.");
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Get form parameters
        String userIdStr = request.getParameter("userId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String accountType = request.getParameter("accountType");
        
        // Validate required fields
        if (userIdStr == null || firstName == null || lastName == null || 
            email == null || accountType == null ||
            userIdStr.trim().isEmpty() || firstName.trim().isEmpty() || 
            lastName.trim().isEmpty() || email.trim().isEmpty() || 
            accountType.trim().isEmpty()) {
            
            session.setAttribute("errorMessage", "Required fields cannot be empty.");
            response.sendRedirect("editUser.jsp?id=" + userIdStr);
            return;
        }
        
        // Validate email format
        Validator validator = new Validator();
        if (!validator.validateEmail(email)) {
            session.setAttribute("errorMessage", "Invalid email format.");
            response.sendRedirect("editUser.jsp?id=" + userIdStr);
            return;
        }
        
        // Validate phone number format if provided
        if (phoneNumber != null && !phoneNumber.trim().isEmpty() && !validator.validatePhoneNumber(phoneNumber)) {
            session.setAttribute("errorMessage", "Invalid phone number format. Please use format: 04XX XXX XXX or +61 X XXXX XXXX");
            response.sendRedirect("editUser.jsp?id=" + userIdStr);
            return;
        }
        
        try {
            int userId = Integer.parseInt(userIdStr);
            UserDAO userDAO = (UserDAO) session.getAttribute("manager");
            
            // Check if email already exists for another user
            User existingUser = userDAO.getUserByEmail(email);
            if (existingUser != null && existingUser.getUserID() != userId) {
                session.setAttribute("errorMessage", "Email already exists for another user.");
                response.sendRedirect("editUser.jsp?id=" + userIdStr);
                return;
            }
            
            // Update user information
            User userToUpdate = userDAO.getUserById(userId);
            if (userToUpdate == null) {
                session.setAttribute("errorMessage", "User not found.");
                response.sendRedirect("ManageUsersServlet");
                return;
            }
            
            userToUpdate.setFirstName(firstName);
            userToUpdate.setLastName(lastName);
            userToUpdate.setEmail(email);
            userToUpdate.setPhoneNumber(phoneNumber != null && !phoneNumber.trim().isEmpty() ? phoneNumber.trim() : null);
            userToUpdate.setAccountType(accountType);
            
            userDAO.updateUser(userToUpdate);
            
            session.setAttribute("successMessage", "User information updated successfully.");
            response.sendRedirect("ManageUsersServlet");
            
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid user ID format.");
            response.sendRedirect("ManageUsersServlet");
        } catch (SQLException e) {
            Logger.getLogger(UpdateUserServlet.class.getName()).log(Level.SEVERE, null, e);
            session.setAttribute("errorMessage", "An error occurred while updating user information.");
            response.sendRedirect("editUser.jsp?id=" + userIdStr);
        }
    }
}