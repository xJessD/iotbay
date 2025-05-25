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

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // Check if user is logged in and is an admin
        if (currentUser == null || !"Admin".equals(currentUser.getAccountType())) {
            session.setAttribute("errorMessage", "You do not have permission to delete users.");
            response.sendRedirect("index.jsp");
            return;
        }
        
        // Get the user ID to delete
        String userIdStr = request.getParameter("userId");
        
        if (userIdStr == null || userIdStr.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Invalid user ID.");
            response.sendRedirect("ManageUsersServlet");
            return;
        }
        
        try {
            int userId = Integer.parseInt(userIdStr);
            
            // Prevent admin from deleting their own account
            if (userId == currentUser.getUserID()) {
                session.setAttribute("errorMessage", "You cannot delete your own admin account.");
                response.sendRedirect("ManageUsersServlet");
                return;
            }
            
            UserDAO userDAO = (UserDAO) session.getAttribute("manager");
            
            // Delete the user
            userDAO.deleteUser(userId);
            
            session.setAttribute("successMessage", "User successfully deleted.");
            response.sendRedirect("ManageUsersServlet");
            
        } catch (NumberFormatException e) {
            session.setAttribute("errorMessage", "Invalid user ID format.");
            response.sendRedirect("ManageUsersServlet");
        } catch (SQLException e) {
            Logger.getLogger(DeleteUserServlet.class.getName()).log(Level.SEVERE, null, e);
            session.setAttribute("errorMessage", "An error occurred while deleting the user.");
            response.sendRedirect("ManageUsersServlet");
        }
    }
}