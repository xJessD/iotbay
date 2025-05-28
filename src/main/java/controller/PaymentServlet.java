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
            request.setAttribute("error", "Please log in to access payment features");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Get the action parameter to determine what to do
        String action = request.getParameter("action");
        
        try {
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            if (paymentDAO == null) {
                // Create PaymentDAO if not in session (should be initialized in ConnServlet)
                request.setAttribute("error", "Payment system unavailable. Please try again later.");
                request.getRequestDispatcher("payment.jsp").forward(request, response);
                return;
            }
            
            if (action == null || action.equals("viewAll")) {
                
                // Get all payments for this customer
                try {
                    int customerID = user.getUserID();
                    List<Payment> payments = paymentDAO.getPaymentsByCustomer(customerID);
                    request.setAttribute("payments", payments);
                    System.out.println("Payments for customer ID " + customerID + ": " + payments);
                    request.getRequestDispatcher("payment_history.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading payment history.");
                }
                
            } else if (action.equals("view")) {
                // View a specific payment
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                Payment payment = paymentDAO.getPayment(paymentID);
                
                if (payment != null) {
                    request.setAttribute("payment", payment);
                    request.getRequestDispatcher("payment_details.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Payment not found");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                }
                
            } else if (action.equals("edit")) {
                // Edit payment form
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                Payment payment = paymentDAO.getPayment(paymentID);
                
                if (payment != null) {
                    // Only allow editing if payment is in Pending status
                    if ("Pending".equals(payment.getPaymentStatus())) {
                        request.setAttribute("payment", payment);
                        request.getRequestDispatcher("edit_payment.jsp").forward(request, response);
                    } else {
                        request.setAttribute("error", "Cannot edit a confirmed payment");
                        request.getRequestDispatcher("payment.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Payment not found");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                }
                
            } else if (action.equals("delete")) {
                // Delete a payment
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                boolean success = paymentDAO.deletePayment(paymentID);
                
                if (success) {
                    // Get the customer ID from request or use a consistent identifier from the user
                    String customerID = request.getParameter("customerID");
                    if (customerID == null) {
                        // Use user email as identifier if customerID not provided
                        customerID = user.getEmail();
                    }
                    request.setAttribute("message", "Payment deleted successfully");
                    request.setAttribute("customerID", customerID);
                    // Set action parameter for the dispatcher
                    request.setAttribute("action", "viewAll");
                    request.getRequestDispatcher("payment_history.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Failed to delete payment. Confirmed payments cannot be deleted.");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
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
            } else if (action.equals("confirm")) {
                // Confirm a payment (change status from Pending to Confirmed)
                int paymentID = Integer.parseInt(request.getParameter("paymentID"));
                boolean success = paymentDAO.confirmPayment(paymentID);
                
                if (success) {
                    Payment payment = paymentDAO.getPayment(paymentID);
                    request.setAttribute("payment", payment);
                    request.setAttribute("message", "Payment confirmed successfully");
                    request.getRequestDispatcher("payment_details.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Failed to confirm payment");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Database error");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid ID format");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (ParseException ex) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            // User not logged in, redirect to login page
            request.setAttribute("error", "Please log in to access payment features");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        
        // Get the action parameter to determine what to do
        String action = request.getParameter("action");
        
        try {
            PaymentDAO paymentDAO = (PaymentDAO) session.getAttribute("paymentDAO");
            if (paymentDAO == null) {
                // Create PaymentDAO  in session (should be initialized in ConnServlet)
                request.setAttribute("error", "Payment system unavailable. Please try again later.");
                request.getRequestDispatcher("payment.jsp").forward(request, response);
                return;
            }
            
            if (action.equals("create")) {
                // Create a new payment
                // int orderID = Integer.parseInt(request.getParameter("orderID"));
                int orderID = 1001;
                int customerID = 5;
                String paymentDateStr = request.getParameter("paymentDate");
                String paymentMethod = request.getParameter("paymentMethod");
                String paymentAmount = request.getParameter("paymentAmount");
                String billingStreetAddress = request.getParameter("billingStreetAddress");
                String billingPostcode = request.getParameter("billingPostcode");
                String billingCity = request.getParameter("billingCity");
                String billingState = request.getParameter("billingState");
                String billingPhoneNumber = request.getParameter("billingPhoneNumber");
                System.out.println("payment info: " + paymentDateStr + ", " + paymentMethod + ", " + paymentAmount + ", " +
                        billingStreetAddress + ", " + billingPostcode + ", " + billingCity + ", " +
                        billingState + ", " + billingPhoneNumber);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date paymentDate = sdf.parse(paymentDateStr);
                
                int paymentID = paymentDAO.createPayment(orderID, customerID, paymentDate, paymentMethod,
                        paymentAmount, billingStreetAddress, billingPostcode, billingCity,
                        billingState, billingPhoneNumber);
                
                if (paymentID > 0) {
                    // Fetch the newly created payment
                    Payment payment = paymentDAO.getPayment(paymentID);
                    request.setAttribute("payment", payment);
                    request.setAttribute("message", "Payment created successfully");
                    request.getRequestDispatcher("payment_details.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Failed to create payment");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
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
                    // Fetch the updated payment
                    Payment payment = paymentDAO.getPayment(paymentID);
                    request.setAttribute("payment", payment);
                    request.setAttribute("message", "Payment updated successfully");
                    request.getRequestDispatcher("payment_details.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Failed to update payment");
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Database error");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid input format");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        } catch (ParseException ex) {
            request.setAttribute("error", "Invalid date format");
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
    }
}
