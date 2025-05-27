<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.Shipment"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Shipment Management - IoTBay</title>
        <link href="css.css" rel="stylesheet" type="text/css">
        <style>
            .shipment-container {
                background-color: white;
                padding: 2rem;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                margin-bottom: 2rem;
            }
            
            .shipment-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 1.5rem;
                padding-bottom: 1rem;
                border-bottom: 1px solid #eee;
            }
            
            .action-buttons {
                display: flex;
                gap: 10px;
                flex-wrap: wrap;
            }
            
            .btn-sm {
                padding: 0.5rem 1rem;
                font-size: 0.9rem;
            }
            
            .shipment-table {
                width: 100%;
                border-collapse: collapse;
            }
            
            .shipment-table th,
            .shipment-table td {
                padding: 0.8rem;
                text-align: left;
                border-bottom: 1px solid #eee;
            }
            
            .shipment-table th {
                background-color: #f5f5f5;
                font-weight: bold;
            }
            
            .shipment-table tr:hover {
                background-color: #f9f9f9;
            }
            
            .status-badge {
                display: inline-block;
                padding: 0.25rem 0.5rem;
                border-radius: 4px;
                font-size: 0.8rem;
                font-weight: bold;
                text-align: center;
            }
            
            .status-pending {
                background-color: #ffc107;
                color: #212529;
            }
            
            .status-processing {
                background-color: #17a2b8;
                color: white;
            }
            
            .status-shipped {
                background-color: #28a745;
                color: white;
            }
            
            .status-delivered {
                background-color: #6c757d;
                color: white;
            }
            
            .status-cancelled {
                background-color: #dc3545;
                color: white;
            }
            
            .empty-state {
                text-align: center;
                padding: 3rem;
                background-color: #f9f9f9;
                border-radius: 8px;
            }
            
            .search-container {
                margin-bottom: 2rem;
                padding: 1.5rem;
                background-color: #f5f5f5;
                border-radius: 8px;
            }
            
            .search-form {
                display: flex;
                flex-wrap: wrap;
                gap: 15px;
                align-items: flex-end;
            }
            
            .search-form .form-group {
                flex: 1;
                min-width: 200px;
                margin-bottom: 0;
            }
        </style>
    </head>

    <body>
        <%@ include file="header.jsp" %>
        
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
            
            String message = (String) request.getAttribute("message");
            String errorMessage = (String) request.getAttribute("errorMessage");
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        %>

        <main>
            <div class="shipment-header">
                <h1>Shipment Management</h1>
                <div class="action-buttons">
                    <a href="ShipmentServlet?action=showCreate" class="form-button-shipment">Create New Shipment</a>
                    <a href="ShipmentSearchServlet" class="form-button-shipment">Search Shipments</a>
                </div>
            </div>
            
            <% if (message != null) { %>
                <div class="success-message">
                    <%= message %>
                </div>
            <% } %>
            
            <% if (errorMessage != null) { %>
                <div class="error-message">
                    <%= errorMessage %>
                </div>
            <% } %>
            
            <div class="shipment-container">
                <% if (shipments == null || shipments.isEmpty()) { %>
                    <div class="empty-state">
                        <h2>No shipments found</h2>
                        <p>You haven't created any shipments yet.</p>
                        <a href="ShipmentServlet?action=showCreate" class="form-button-shipment">Create Your First Shipment</a>
                    </div>
                <% } else { %>
                    <table class="shipment-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Order ID</th>
                                <th>Method</th>
                                <th>Address</th>
                                <th>Status</th>
                                <th>Created</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Shipment shipment : shipments) { %>
                                <tr>
                                    <td><%= shipment.getShipmentID() %></td>
                                    <td><%= shipment.getOrderID() %></td>
                                    <td><%= shipment.getShipmentMethod() %></td>
                                    <td><%= shipment.getStreetAddress() + ", " + shipment.getCity() %></td>
                                    <td>
                                        <span class="status-badge status-<%= shipment.getStatus().toLowerCase() %>">
                                            <%= shipment.getStatus() %>
                                        </span>
                                    </td>
                                    <td>
                                        <% if (shipment.getCreatedDate() != null) { %>
                                            <%= dateFormat.format(shipment.getCreatedDate()) %>
                                        <% } else { %>
                                            Not available
                                        <% } %>
                                    </td>
                                    <td>
                                        <div class="action-buttons">
                                            <a href="ShipmentServlet?action=view&id=<%= shipment.getShipmentID() %>" class="form-button-shipment">View</a>
                                            
                                            <% if (!shipment.isFinalized()) { %>
                                                <a href="ShipmentServlet?action=showEdit&id=<%= shipment.getShipmentID() %>" class="form-button-shipment">Edit</a>
                                                <a href="DeleteShipmentServlet?id=<%=shipment.getShipmentID()%>" class="form-button-shipment">Delete</a>
                                            <% } %>
                                        </div>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
        </main>

        <%@ include file="footer.jsp" %>
        
        <script>
            function confirmDelete(shipmentId) {
                if (confirm("Are you sure you want to delete this shipment? This action cannot be undone.")) {
                    window.location.href = "ShipmentServlet?action=delete&id=" + shipmentId;
                }
            }
        </script>
    </body>
</html>