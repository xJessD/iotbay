<%@ page import="model.User, model.Payment, java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Payment Details</title>
    <link href="css.css" rel="stylesheet" type="text/css">
    <style>
        .payment-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        
        .payment-details {
            margin-top: 20px;
        }
        
        .detail-section {
            margin-bottom: 25px;
        }
        
        .detail-section h3 {
            margin-bottom: 15px;
            padding-bottom: 5px;
            border-bottom: 1px solid #eee;
        }
        
        .detail-row {
            display: flex;
            margin-bottom: 10px;
        }
        
        .detail-label {
            width: 200px;
            font-weight: 600;
            color: #555;
        }
        
        .detail-value {
            flex: 1;
        }
        
        .btn-group {
            margin-top: 30px;
            text-align: right;
        }
        
        .btn {
            padding: 8px 16px;
            background-color: #F96E46;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
            text-decoration: none;
            display: inline-block;
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
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main>
        <div class="payment-container">
            <h2>Payment Details</h2>
            
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
                    You must be logged in to view payment details.
                    <a href="login.jsp">Log in here</a>
                </div>
            <% 
            } else {
                Payment payment = (Payment)request.getAttribute("payment");
                
                if (payment != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            %>
                <div class="payment-details">
                    <div class="detail-section">
                        <h3>Payment Information</h3>
                        
                        <div class="detail-row">
                            <div class="detail-label">Payment ID:</div>
                            <div class="detail-value"><%= payment.getPaymentID() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Order ID:</div>
                            <div class="detail-value"><%= payment.getOrderID() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Date:</div>
                            <div class="detail-value"><%= dateFormat.format(payment.getPaymentDate()) %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Amount:</div>
                            <div class="detail-value"><%= payment.getPaymentAmount() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Payment Method:</div>
                            <div class="detail-value"><%= payment.getPaymentMethod() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Payment Status:</div>
                            <div class="detail-value"><%= payment.getPaymentStatus() %></div>
                        </div>
                    </div>
                    
                    <div class="detail-section">
                        <h3>Billing Information</h3>
                        
                        <div class="detail-row">
                            <div class="detail-label">Street Address:</div>
                            <div class="detail-value"><%= payment.getBillingStreetAddress() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">City:</div>
                            <div class="detail-value"><%= payment.getBillingCity() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">State:</div>
                            <div class="detail-value"><%= payment.getBillingState() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Postcode:</div>
                            <div class="detail-value"><%= payment.getBillingPostcode() %></div>
                        </div>
                        
                        <div class="detail-row">
                            <div class="detail-label">Phone Number:</div>
                            <div class="detail-value"><%= payment.getBillingPhoneNumber() %></div>
                        </div>
                    </div>
                    
                    <div class="btn-group">
                        <a href="PaymentServlet?action=viewAll&customerID=<%= payment.getCustomerID() %>" class="btn btn-secondary">Back to Payments</a>
                        <% if ("Pending".equals(payment.getPaymentStatus())) { %>
                            <a href="PaymentServlet?action=edit&paymentID=<%= payment.getPaymentID() %>" class="btn">Edit Payment</a>
                            <a href="PaymentServlet?action=confirm&paymentID=<%= payment.getPaymentID() %>" class="btn btn-success" onclick="return confirm('Are you sure you want to confirm this payment? This action cannot be undone.')">Confirm Payment</a>
                            <a href="javascript:confirmDelete(<%= payment.getPaymentID() %>)" class="btn btn-danger">Delete Payment</a>
                        <% } %>
                    </div>
                </div>
            <% 
                } else {
            %>
                <div class="alert alert-danger">
                    Payment details not found.
                </div>
                <div class="btn-group">
                    <a href="PaymentServlet?action=viewAll&customerID=1" class="btn">Back to Payments</a>
                </div>
            <% 
                }
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