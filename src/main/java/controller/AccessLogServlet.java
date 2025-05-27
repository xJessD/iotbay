package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.AccessLog;
import model.User;
import model.dao.AccessLogDAO;

@WebServlet("/accessLogs")
public class AccessLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        AccessLogDAO accessLogDAO = (AccessLogDAO) session.getAttribute("accessLog");

        try {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");

            LocalDateTime startDateTime = null;
            LocalDateTime endDateTime = null;

            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDateTime = LocalDate.parse(startDateStr).atStartOfDay();
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDateTime = LocalDate.parse(endDateStr).atTime(LocalTime.MAX);
            }

            // Validate that start date is not after end date
            if (startDateTime != null && endDateTime != null && startDateTime.isAfter(endDateTime)) {
                session.setAttribute("errorMessage", "Start date cannot be after end date.");
                request.setAttribute("logs", accessLogDAO.getUserLogs(user.getUserID()));
                request.getRequestDispatcher("logs.jsp").forward(request, response);
                return;
            }

            List<AccessLog> logs;
            if (startDateTime != null && endDateTime != null) {
                logs = accessLogDAO.getUserLogsByDateRange(user.getUserID(), startDateTime, endDateTime);
            } else {
                logs = accessLogDAO.getUserLogs(user.getUserID());
            }

            request.setAttribute("logs", logs);
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "An error occurred while fetching access logs.");
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Invalid date format. Please use YYYY-MM-DD format.");
        }

        request.getRequestDispatcher("logs.jsp").forward(request, response);
    }
}