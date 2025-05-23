<%@ page import="model.ProductCatalog" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <c:choose>
            <c:when test="${not empty products}">
                <c:forEach var="product" items="${products}">
                    <div class="product-card">
                        <img src="${pageContext.request.contextPath}/${product.imageUrl}" alt="${product.name}">
                        <h5>${product.name}</h5>
                        <p>${product.description}</p>
                        <p class="price">$${product.price}</p>
                        <p>Quantity: ${product.quantity}</p>
                        <p>Favourited: ${product.favourited ? "❤️" : "No"}</p>
                        <div class="btn-group mt-2">
                            <a href="editproduct.jsp?productID=${product.productID}" class="btn btn-primary btn-sm">Edit</a>
                            <form action="products/delete" method="post" style="display:inline;">
                                <input type="hidden" name="productID" value="${product.productID}" />
                                <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>No products available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
