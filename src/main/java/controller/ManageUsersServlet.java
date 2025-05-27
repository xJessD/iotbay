package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

@WebServlet("/ManageUsersServlet")
public class ManageUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // Check if user is logged in and is an admin
        if (currentUser == null || !"Admin".equals(currentUser.getAccountType())) {
            session.setAttribute("errorMessage", "You do not have permission to access this feature.");
            response.sendRedirect("index.jsp");
            return;
        }
        
        UserDAO userDAO = (UserDAO) session.getAttribute("manager");
        String searchTerm = request.getParameter("searchTerm");
        
        try {
            List<User> users;
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                // Search for users by the search term
                users = userDAO.searchUsers(searchTerm);
            } else {
                // Get all users
                users = userDAO.fetchAllUsers();
            }
            
            request.setAttribute("users", users);
            request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUsersServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("errorMessage", "An error occurred while retrieving users.");
            response.sendRedirect("index.jsp");
        }
    }
}