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

        UserDAO manager = (UserDAO) session.getAttribute("manager");

        User user = null;

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
            user.setAccountType("Customer");
            // 4- Add the user to the database
            manager.addUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(),
                    user.getPhoneNumber());
            // 5- Redirect to the login page with success message
            session.setAttribute("successMessage", "Registration successful. Please log in.");
            response.sendRedirect("/iotbay/login.jsp");
            return;
        } catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
