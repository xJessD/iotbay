<%@ page import="model.Product" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css.css">
    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 1.5rem;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 1rem;
            text-align: center;
            position: relative;
        }
        .product-card img {
            max-width: 100%;
            height: 160px;
            object-fit: contain;
            margin-bottom: 1rem;
        }
        .product-card .price {
            color: #F96E46;
            font-weight: bold;
        }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Product Management</h2>

    <div class="text-right mb-4">
        <a href="addproduct.jsp" class="btn btn-success">Add New Product</a>
    </div>

    <div class="product-grid">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Product product : products) {
        %>
        <div class="product-card">
            <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>">
            <h5><%= product.getName() %></h5>
            <p><%= product.getDescription() %></p>
            <p class="price">$<%= String.format("%.2f", product.getPrice()) %></p>
            <p>Quantity: <%= product.getQuantity() %></p>
            <p>Favourited: <%= product.isFavourited() ? "❤️" : "No" %></p>
            <div class="btn-group mt-2">
                <a href="editproduct.jsp?productID=<%= product.getProductID() %>" class="btn btn-primary btn-sm">Edit</a>
                <form action="products/delete" method="post" style="display:inline;">
                    <input type="hidden" name="ProductID" value="<%= product.getProductID() %>" />
                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                </form>
            </div>
        </div>
        <% } } else { %>
        <p>No products available.</p>
        <% } %>
    </div>
</div>
</body>
</html>
