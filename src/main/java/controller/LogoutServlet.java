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
import model.dao.AccessLogDAO;
import model.dao.UserDAO;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO manager = (UserDAO) session.getAttribute("manager");

        if (user != null) {
            AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLog");
            try {
                accessLogDAO.updateLogoutTime(user.getUserID(), LocalDateTime.now());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
}
