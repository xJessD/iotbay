<<<<<<< HEAD
package controller;

import model.User;
import model.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Simple validation
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=Email+and+password+are+required");
            return;
        }
        
        // Authenticate user
        UserDAO userDAO = UserDAO.getInstance();
        User user = userDAO.authenticate(email, password);
        
        if (user != null) {
            // Create session for user
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            // Redirect to main page
            response.sendRedirect("main.jsp");
        } else {
            // Authentication failed
            response.sendRedirect("login.jsp?error=Invalid+email+or+password");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to login page
        response.sendRedirect("login.jsp");
    }
}
=======

>>>>>>> 5f6be21519bd5ef234db77a96374868f511fb70a
