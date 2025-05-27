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

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        UserDAO manager = (UserDAO) session.getAttribute("manager");
        
        try {
            // Delete the user from the database
            manager.deleteUser(user.getUserID());
            
            // Invalidate the session
            session.invalidate();
            
            // Create a new session for the success message
            session = request.getSession();
            session.setAttribute("successMessage", "Your account has been successfully deleted.");
            
            // Redirect to the landing page
            response.sendRedirect("index.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(DeleteAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("errorMessage", "An error occurred while deleting your account. Please try again.");
            response.sendRedirect("account.jsp");
        }
    }
}