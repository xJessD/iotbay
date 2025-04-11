package controller;

import model.User;
import model.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
   
    // Email validation expression
    private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    //Boolean for checking email format against pattern
    private boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from request
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String tosValue = request.getParameter("TOS");
        boolean agreedToTOS = "on".equals(tosValue) || "true".equals(tosValue);
       
        // All input fields null/empty check
        if (fname == null || fname.isEmpty() ||
            lname == null || lname.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            !agreedToTOS) {
           
            response.sendRedirect("register.jsp?error=All+fields+are+required");
            return;
        }
        
        // Use email formatting boolean
        if (!isValidEmail(email)) {
            response.sendRedirect("register.jsp?error=Please+enter+a+valid+email+address");
            return;
        }
        
        // Check if email already exists in UserDAO
        UserDAO userDAO = UserDAO.getInstance();
        if (userDAO.findByEmail(email) != null) {
            response.sendRedirect("register.jsp?error=Email+already+registered");
            return;
        }
       
        // Create new User
        User user = new User();
        user.setFname(fname);
        user.setLname(lname);
        user.setEmail(email);
        user.setPassword(password);
        
        // Save User to UserDAO
        boolean saved = userDAO.save(user);
        
        if (!saved) {
            response.sendRedirect("register.jsp?error=Registration+failed");
            return;
        }
       
        // Save User to session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
       
       
        // Redirect to main page
        response.sendRedirect("main.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to register page
        response.sendRedirect("register.jsp");
    }
}