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
import model.dao.AccessLogDAO;

@WebServlet("/DeleteLogServlet")
public class DeleteLogServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in and is an admin
        if (user == null || !"Admin".equals(user.getAccountType())) {
            session.setAttribute("errorMessage", "You do not have permission to delete logs.");
            response.sendRedirect("accessLogs");
            return;
        }
        
        AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLog");
        String action = request.getParameter("action");
        
        try {
            if ("deleteOne".equals(action)) {
                // Delete a single log
                String logIdStr = request.getParameter("logId");
                if (logIdStr != null && !logIdStr.isEmpty()) {
                    int logId = Integer.parseInt(logIdStr);
                    accessLogDAO.deleteLog(logId);
                    session.setAttribute("successMessage", "Log entry deleted successfully.");
                } else {
                    session.setAttribute("errorMessage", "Invalid log ID.");
                }
            } else if ("deleteAll".equals(action)) {
                // Delete all logs for a user
                String userIdStr = request.getParameter("userId");
                if (userIdStr != null && !userIdStr.isEmpty()) {
                    int userId = Integer.parseInt(userIdStr);
                    accessLogDAO.deleteAllUserLogs(userId);
                    session.setAttribute("successMessage", "All log entries deleted successfully.");
                } else {
                    session.setAttribute("errorMessage", "Invalid user ID.");
                }
            } else {
                session.setAttribute("errorMessage", "Invalid action specified.");
            }
        } catch (NumberFormatException e) {
            Logger.getLogger(DeleteLogServlet.class.getName()).log(Level.SEVERE, "Invalid ID format", e);
            session.setAttribute("errorMessage", "Invalid ID format.");
        } catch (SQLException e) {
            Logger.getLogger(DeleteLogServlet.class.getName()).log(Level.SEVERE, "Database error", e);
            session.setAttribute("errorMessage", "An error occurred while deleting logs.");
        }
        
        // Redirect back to the access logs page
        response.sendRedirect("accessLogs");
    }
}