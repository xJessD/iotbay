<%@ page import="model.User, model.Payment, java.text.SimpleDateFormat" %>
<html>
<head>
    <title>Edit Payment</title>
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
        
        .alert-warning {
            background-color: #fff3cd;
            color: #856404;
            border: 1px solid #ffeeba;
        }
        
        .alert-info {
            background-color: #d1ecf1;
            color: #0c5460;
            border: 1px solid #bee5eb;
        }
    </style>
</head>
<body>
    <%@ include file="header.jsp" %>

    <main>
        <div class="payment-container">
            <h2>Edit Payment</h2>
            
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
            
            Payment payment = (Payment)request.getAttribute("payment");
            if (payment == null) {
            %>
                <div class="alert alert-danger">
                    No payment information available.
                </div>
            <% 
            } else {
                if (!"Pending".equals(payment.getPaymentStatus())) {
            %>
                <div class="alert alert-warning">
                    This payment is already confirmed and cannot be edited.
                </div>
            <%
                } else {
            %>
                <div class="alert alert-info">
                    You are editing a pending payment. Once a payment is confirmed, it cannot be modified.
                </div>
            <% 
                }
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            %>
                <form class="payment-form" action="PaymentServlet" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="paymentID" value="<%= payment.getPaymentID() %>">
                    
                    <div class="form-section">
                        <h3>Payment Method</h3>
                        
                        <div class="form-group">
                            <label for="paymentMethod">Payment Method:</label>
                            <select id="paymentMethod" name="paymentMethod" required>
                                <option value="Credit Card" <%= payment.getPaymentMethod().equals("Credit Card") ? "selected" : "" %>>Credit Card</option>
                                <option value="Debit Card" <%= payment.getPaymentMethod().equals("Debit Card") ? "selected" : "" %>>Debit Card</option>
                                <option value="PayPal" <%= payment.getPaymentMethod().equals("PayPal") ? "selected" : "" %>>PayPal</option>
                                <option value="Bank Transfer" <%= payment.getPaymentMethod().equals("Bank Transfer") ? "selected" : "" %>>Bank Transfer</option>
                            </select>
                        </div>
                        
                        <div id="creditCardFields" style="display:<%= payment.getPaymentMethod().equals("Credit Card") || payment.getPaymentMethod().equals("Debit Card") ? "block" : "none" %>;">
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
                                <input type="text" id="billingStreetAddress" name="billingStreetAddress" value="<%= payment.getBillingStreetAddress() %>" required>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="billingCity">City:</label>
                                <input type="text" id="billingCity" name="billingCity" value="<%= payment.getBillingCity() %>" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="billingState">State:</label>
                                <input type="text" id="billingState" name="billingState" value="<%= payment.getBillingState() %>" required>
                            </div>
                            
                            <div class="form-group">
                                <label for="billingPostcode">Postcode:</label>
                                <input type="text" id="billingPostcode" name="billingPostcode" value="<%= payment.getBillingPostcode() %>" required>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="billingPhoneNumber">Phone Number:</label>
                                <input type="text" id="billingPhoneNumber" name="billingPhoneNumber" value="<%= payment.getBillingPhoneNumber() %>" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-section">
                        <h3>Payment Details</h3>
                        
                        <div class="form-row">
                            <div class="form-group">
                                <label for="paymentAmount">Amount:</label>
                                <input type="text" id="paymentAmount" name="paymentAmount" value="<%= payment.getPaymentAmount() %>" required>
                            </div>
                        </div>
                    </div>
                    
                    <div class="btn-group">
                        <a href="PaymentServlet?action=view&paymentID=<%= payment.getPaymentID() %>" class="btn btn-secondary">Cancel</a>
                        <button type="submit" class="btn">Save Changes</button>
                    </div>
                </form>
            <% 
                }
            %>
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