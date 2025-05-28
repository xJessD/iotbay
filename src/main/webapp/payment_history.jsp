<%@ page import="model.User, model.Payment, java.util.List, java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Payment History</title>
    <link href="css.css" rel="stylesheet" type="text/css">
    <style>
        .payment-container {
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        .search-section {
            margin-bottom: 25px;
            padding: 15px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        
        .search-form {
            display: flex;
            align-items: flex-end;
            flex-wrap: wrap;
            gap: 15px;
        }
        
        .form-group {
            flex: 1;
            min-width: 150px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        .btn {
            padding: 8px 16px;
            background-color: #F96E46;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin: 2px;
        }
        
        .btn:hover {
            background-color: #e85d35;
        }
        
        .btn-secondary {
            background-color: #6c757d;
        }
        
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        
        .btn-danger {
            background-color: #dc3545;
        }
        
        .btn-danger:hover {
            background-color: #c82333;
        }
        
        .btn-success {
            background-color: #28a745;
        }
        
        .btn-success:hover {
            background-color: #218838;
        }
        
        .alert {
            padding: 10px 15px;
            margin-bottom: 20px;
            border-radius: 4px;
        }
        
        .alert-danger {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        th {
            background-color: #f4f4f4;
            font-weight: 600;
        }
        
        tr:hover {
            background-color: #f9f9f9;
        }
        
        .payment-actions {
            display: flex;
            gap: 5px;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main>
        <div class="payment-container">
            <h2>Payment History</h2>
            
            <% 
            // Display any error or success messages
            String error = request.getParameter("error");
            String message = request.getParameter("message");
            if (error != null && !error.isEmpty()) {
            %>
                <div class="alert alert-danger">
                    <%= error %>
                </div>
            <% 
            }
            if (message != null && !message.isEmpty()) {
            %>
                <div class="alert alert-success">
                    <%= message %>
                </div>
            <% 
            }
            
            // Check if user is logged in
            User paymentUser = (User)session.getAttribute("user");
            if (paymentUser == null) {
            %>
                <div class="alert alert-danger">
                    You must be logged in to view your payment history.
                    <a href="login.jsp">Log in here</a>
                </div>
            <% 
            } else {
                List<Payment> payments = (List<Payment>)request.getAttribute("payments");
                
                // Date information for search
                String startDate = (String)request.getAttribute("startDate");
                String endDate = (String)request.getAttribute("endDate");
                if (startDate == null) startDate = "";
                if (endDate == null) endDate = "";
            %>
                <div class="search-section">
                    <h3>Search Payments</h3>
                    <form class="search-form" action="PaymentServlet" method="get">
                        <input type="hidden" name="action" value="search">
                        <input type="hidden" name="customerID" value="1"> <!-- This would be the actual customer ID -->
                        
                        <div class="form-group">
                            <label for="startDate">From Date:</label>
                            <input type="date" id="startDate" name="startDate" value="<%= startDate %>">
                        </div>
                        
                        <div class="form-group">
                            <label for="endDate">To Date:</label>
                            <input type="date" id="endDate" name="endDate" value="<%= endDate %>">
                        </div>
                        
                        <button type="submit" class="btn">Search</button>
                        <a href="PaymentServlet?action=viewAll&customerID=1" class="btn btn-secondary">Clear</a>
                    </form>
                </div>
                
                <%-- <div class="action-buttons">
                    <a href="payment.jsp" class="btn">Add New Payment</a>
                </div> --%>
                
                <% if (payments != null && !payments.isEmpty()) { 
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                %>
                    <table>
                        <thead>
                            <tr>
                                <th>Payment ID</th>
                                <th>Date</th>
                                <th>Amount</th>
                                <th>Method</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Payment payment : payments) { %>
                                <tr>
                                    <td><%= payment.getPaymentID() %></td>
                                    <td><%= dateFormat.format(payment.getPaymentDate()) %></td>
                                    <td><%= payment.getPaymentAmount() %></td>
                                    <td><%= payment.getPaymentMethod() %></td>
                                    <td><%= payment.getPaymentStatus() %></td>
                                    <td class="payment-actions">
                                        <a href="PaymentServlet?action=view&paymentID=<%= payment.getPaymentID() %>" class="btn">View</a>
                                        <% if ("Pending".equals(payment.getPaymentStatus())) { %>
                                            <a href="PaymentServlet?action=edit&paymentID=<%= payment.getPaymentID() %>" class="btn btn-secondary">Edit</a>
                                            <a href="PaymentServlet?action=confirm&paymentID=<%= payment.getPaymentID() %>" class="btn btn-success">Confirm</a>
                                            <a href="javascript:confirmDelete(<%= payment.getPaymentID() %>)" class="btn btn-danger">Delete</a>
                                        <% } %>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } else { %>
                    <div style="margin-top: 20px; text-align: center;">
                        <p>No payment records found.</p>
                    </div>
                <% } 
                }
                %>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
    
    <script>
        function confirmDelete(paymentID) {
            if (confirm("Are you sure you want to delete this payment record? This action cannot be undone.")) {
                window.location.href = "PaymentServlet?action=delete&paymentID=" + paymentID;
            }
        }
    </script>
</body>
</html>