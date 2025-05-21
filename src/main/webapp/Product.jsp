<%@ page import="java.util.*, model.Product" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>IoTBay Products</title>
<link rel="stylesheet" href="styles/site.css">
<link rel="stylesheet" href="styles/products.css">
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<main>
<h2>Explore Our IoT Devices</h2>
<div class="product-grid">
<%
List<Product> products = (List<Product>) request.getAttribute("products");
if (products != null && !products.isEmpty()) {
for (Product product : products) {
%>
<div class="product-card">
<div class="favourite-icon">
<% if (product.isFavourited()) { %>
❤️
<% } else { %>
♡
<% } %>
</div>
<img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>">
<h3><%= product.getName() %></h3>
<p><%= product.getDescription() %></p>
<span class="price">$<%= String.format("%.2f", product.getPrice()) %></span>
<div class="quantity">In Stock: <%= product.getQuantity() %></div>
<button class="buy-btn" <%= product.getQuantity() == 0 ? "disabled" : "" %>>
<%= product.getQuantity() == 0 ? "Out of Stock" : "Buy Now" %>
</button>
</div>
<% }
} else {
%>
<p>No products available at the moment.</p>
<% } %>
</div>
</main>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>