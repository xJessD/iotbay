package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Payment;
import model.User;
import model.dao.PaymentDAO;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp?error=Please log in to access payment features");
            return;
        }
        
        // Get the action parameter to determine what to do
        String action = request.getParameter("action");
        
        try {
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            if (paymentDAO == null) {
                // Create PaymentDAO if not in session (should be initialized in ConnServlet)
                return;
            }
            
            if (action == null || action.equals("viewAll")) {
                // Get the customer ID (this would need to be fixed based on your actual data model)
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                
                // Get all payments for this customer
                List<Payment> payments = paymentDAO.getPaymentsByCustomer(customerID);
                request.setAttribute("payments", payments);
                request.getRequestDispatcher("payment_history.jsp").forward(request, response);
                
            } else if (action.equals("view")) {
                // View a specific payment
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                Payment payment = paymentDAO.getPayment(paymentID);
                
                if (payment != null) {
                    request.setAttribute("payment", payment);
                    request.getRequestDispatcher("payment_details.jsp").forward(request, response);
                } else {
                    response.sendRedirect("payment.jsp?error=Payment not found");
                }
                
            } else if (action.equals("edit")) {
                // Edit payment form
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                Payment payment = paymentDAO.getPayment(paymentID);
                
                if (payment != null) {
                    request.setAttribute("payment", payment);
                    request.getRequestDispatcher("edit_payment.jsp").forward(request, response);
                } else {
                    response.sendRedirect("payment.jsp?error=Payment not found");
                }
                
            } else if (action.equals("delete")) {
                // Delete a payment
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                boolean success = paymentDAO.deletePayment(paymentID);
                
                if (success) {
                    response.sendRedirect("PaymentServlet?action=viewAll&customerID=" + user.getEmail() + "&message=Payment deleted successfully");
                } else {
                    response.sendRedirect("payment.jsp?error=Failed to delete payment");
                }
                
            } else if (action.equals("search")) {
                // Search payments by date
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                String startDateStr = request.getParameter("startDate");
                String endDateStr = request.getParameter("endDate");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf.parse(startDateStr);
                Date endDate = sdf.parse(endDateStr);
                
                List<Payment> payments = paymentDAO.searchPaymentsByDateRange(customerID, startDate, endDate);
                request.setAttribute("payments", payments);
                request.setAttribute("startDate", startDateStr);
                request.setAttribute("endDate", endDateStr);
                request.getRequestDispatcher("payment_history.jsp").forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("payment.jsp?error=Database error");
        } catch (NumberFormatException ex) {
            response.sendRedirect("payment.jsp?error=Invalid ID format");
        } catch (ParseException ex) {
            response.sendRedirect("payment.jsp?error=Invalid date format");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // User not logged in, redirect to login page
            response.sendRedirect("login.jsp?error=Please log in to access payment features");
            return;
        }
        
        // Get the action parameter to determine what to do
        String action = request.getParameter("action");
        
        try {
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            if (paymentDAO == null) {
                // Create PaymentDAO if not in session (should be initialized in ConnServlet)
                return;
            }
            
            if (action.equals("create")) {
                // Create a new payment
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int customerID = Integer.parseInt(request.getParameter("customerID"));
                String paymentDateStr = request.getParameter("paymentDate");
                String paymentMethod = request.getParameter("paymentMethod");
                String paymentAmount = request.getParameter("paymentAmount");
                String billingStreetAddress = request.getParameter("billingStreetAddress");
                String billingPostcode = request.getParameter("billingPostcode");
                String billingCity = request.getParameter("billingCity");
                String billingState = request.getParameter("billingState");
                String billingPhoneNumber = request.getParameter("billingPhoneNumber");
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date paymentDate = sdf.parse(paymentDateStr);
                
                int paymentID = paymentDAO.createPayment(orderID, customerID, paymentDate, paymentMethod,
                        paymentAmount, billingStreetAddress, billingPostcode, billingCity,
                        billingState, billingPhoneNumber);
                
                if (paymentID > 0) {
                    response.sendRedirect("PaymentServlet?action=view&paymentID=" + paymentID);
                } else {
                    response.sendRedirect("payment.jsp?error=Failed to create payment");
                }
                
            } else if (action.equals("update")) {
                // Update an existing payment
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                String paymentMethod = request.getParameter("paymentMethod");
                String paymentAmount = request.getParameter("paymentAmount");
                String billingStreetAddress = request.getParameter("billingStreetAddress");
                String billingPostcode = request.getParameter("billingPostcode");
                String billingCity = request.getParameter("billingCity");
                String billingState = request.getParameter("billingState");
                String billingPhoneNumber = request.getParameter("billingPhoneNumber");
                
                boolean success = paymentDAO.updatePayment(paymentID, paymentMethod, paymentAmount,
                        billingStreetAddress, billingPostcode, billingCity, billingState, billingPhoneNumber);
                
                if (success) {
                    response.sendRedirect("PaymentServlet?action=view&paymentID=" + paymentID + "&message=Payment updated successfully");
                } else {
                    response.sendRedirect("payment.jsp?error=Failed to update payment");
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("payment.jsp?error=Database error");
        } catch (NumberFormatException ex) {
            response.sendRedirect("payment.jsp?error=Invalid input format");
        } catch (ParseException ex) {
            response.sendRedirect("payment.jsp?error=Invalid date format");
        }
    }
}
