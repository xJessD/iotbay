<%@ page import="model.User, model.Payment, java.util.List" %>
<html>
<head>
    <title>Payment Page</title>
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
        
        .payment-form {
            margin-top: 20px;
        }
        
        .form-section {
            margin-bottom: 25px;
        }
        
        .form-section h3 {
            margin-bottom: 15px;
            padding-bottom: 5px;
            border-bottom: 1px solid #eee;
        }
        
        .form-row {
            display: flex;
            flex-wrap: wrap;
            margin-bottom: 15px;
        }
        
        .form-group {
            flex: 1;
            min-width: 200px;
            margin-right: 15px;
        }
        
        .form-group:last-child {
            margin-right: 0;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }
        
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        .btn-group {
            margin-top: 20px;
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
            <h2>Make a Payment</h2>
            
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
                    You must be logged in to access payment features.
                    <a href="login.jsp">Log in here</a>
                </div>
            <% 
            } else {
                // Get order ID from request or set default for testing
                String orderIDStr = request.getParameter("orderID");
                int orderID = 0;
                try {
                    orderID = Integer.parseInt(orderIDStr);
                } catch (Exception e) {
                    // Use default for testing
                    orderID = 1;
                }
            %>
                <form class="payment-form" action="PaymentServlet" method="post">
                    <input type="hidden" name="action" value="create">
                    <input type="hidden" name="orderID" value="<%= orderID %>">
                    <input type="hidden" name="customerID" value="1"> <!-- This would be the actual customer ID -->
                    <input type="hidden" name="paymentDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                    
                    <div class="form-section">
                        <h3>Payment Method</h3>
                        
                        <div class="form-group">
                            <label for="paymentMethod">Payment Method:</label>
                            <select id="paymentMethod" name="paymentMethod" required>
                                <option value="">Select Payment Method</option>
                                <option value="Credit Card">Credit Card</option>
                                <option value="Debit Card">Debit Card</option>
                                <option value="PayPal">PayPal</option>
                                <option value="Bank Transfer">Bank Transfer</option>
                            </select>
                        </div>
                        
                        <div id="creditCardFields" style="display:none;">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="cardNumber">Card Number:</label>
                                    <input type="text" id="cardNumber" name="cardNumber" placeholder="1234 5678 9012 3456">
                                </div>
                                
                                <div class="form-group">
                                    <label for="cardHolder">Card Holder:</label>
                                    <input type="text" id="cardHolder" name="cardHolder" placeholder="John Doe">
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="expiryDate">Expiry Date:</label>
                                    <input type="text" id="expiryDate" name="expiryDate" placeholder="MM/YY">
                                </div>
                                
                                <div class="form-group">
                                    <label for="cvv">CVV:</label>
                                    <input type="text" id="cvv" name="cvv" placeholder="123">
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-section">
                        <h3>Billing Information</h3>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="billingStreetAddress">Street Address:</label>
                                <input type="text" id="billingStreetAddress" name="billingStreetAddress" required>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="billingCity">City:</label>
                                <input type="text" id="billingCity" name="billingCity" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="billingState">State:</label>
                                <input type="text" id="billingState" name="billingState" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="billingPostcode">Postcode:</label>
                                <input type="text" id="billingPostcode" name="billingPostcode" required>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="billingPhoneNumber">Phone Number:</label>
                                <input type="text" id="billingPhoneNumber" name="billingPhoneNumber" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-section">
                        <h3>Payment Details</h3>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="paymentAmount">Amount:</label>
                                <input type="text" id="paymentAmount" name="paymentAmount" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="btn-group">
                        <button type="button" class="btn btn-secondary" onclick="window.history.back();">Cancel</button>
                        <button type="submit" class="btn">Submit Payment</button>
                    </div>
                </form>
                
                <div style="margin-top: 30px;">
                    <h3>Payment Options</h3>
                    <p>
                        <a href="PaymentServlet?action=viewAll&customerID=1" class="btn">View Payment History</a>
                    </p>
                </div>
            <% } %>
        </div>
    </main>

    <%@ include file="footer.jsp" %>
    
    <script>
        // Show/hide credit card fields based on payment method selection
        document.getElementById('paymentMethod').addEventListener('change', function() {
            const creditCardFields = document.getElementById('creditCardFields');
            if (this.value === 'Credit Card' || this.value === 'Debit Card') {
                creditCardFields.style.display = 'block';
            } else {
                creditCardFields.style.display = 'none';
            }
        });
    </script>
</body>
</html>