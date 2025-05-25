package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.AccessLog;
import model.dao.UserDAO;
import model.dao.AccessLogDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1- retrieve the current session
        HttpSession session = request.getSession();
        
        // Clear any previous error messages
        session.removeAttribute("errorMessage");

        // 2- create an instance of the Validator class
        Validator validator = new Validator();

        // 3- capture the posted email
        String email = request.getParameter("email");

        // 4- capture the posted password
        String password = request.getParameter("password");

        // 5- retrieve the manager instance from session
        UserDAO manager = (UserDAO) session.getAttribute("manager");
        AccessLogDAO accessLogDAO = (AccessLogDAO)session.getAttribute("accessLog");

        User user = null;

        // Check for empty fields first
        if (email == null || email.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Email cannot be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        if (password == null || password.trim().isEmpty()) {
            session.setAttribute("errorMessage", "Password cannot be empty.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!validator.validateEmail(email)) {
            session.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            // Try to find the user
            user = manager.findUser(email, password);
            
            if (user != null) {
                // User found - successful login
                session.setAttribute("user", user);
    
                // Clear any error messages and set success message
                session.removeAttribute("errorMessage");
                session.setAttribute("successMessage", "Login successful!");

                // Log the login time
                LocalDateTime now = LocalDateTime.now();
                AccessLog accessLog = new AccessLog(user.getUserID(), now);

                // Log the login time to the database
                try {
                    accessLogDAO.addAccessLog(accessLog);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Redirect user to the main page
                response.sendRedirect("index.jsp");
                return;
            } else {
                // User not found - check if email exists to give more specific error
                if (manager.emailExists(email)) {
                    session.setAttribute("errorMessage", "Incorrect password. Please try again.");
                } else {
                    session.setAttribute("errorMessage", "No account found with this email. Please register.");
                }
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            session.setAttribute("errorMessage", "A database error occurred. Please try again later.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
    }
}