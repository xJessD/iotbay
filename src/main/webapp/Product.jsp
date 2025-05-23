<%@ page import="main.java.model.ProductCatalog" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Catalog</title>
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
        .product-card h5 {
            margin-bottom: 0.5rem;
        }
        .product-card .price {
            color: #F96E46;
            font-weight: bold;
        }
        .favourite-icon {
            font-size: 1.4rem;
            color: #F96E46;
            position: absolute;
            top: 10px;
            right: 15px;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">IoTBay Products</h2>
    <div class="product-grid">
        <c:forEach var="p" items="${products}">
            <div class="product-card">
                <div class="favourite-icon">
                    ${p.favourited ? "❤️" : "♡"}
                </div>
                <img src="${pageContext.request.contextPath}/${p.imageUrl}" alt="${p.name}">
                <h5>${p.name}</h5>
                <p>${p.description}</p>
                <p class="price">$${p.price}</p>
                <p>In Stock: ${p.quantity}</p>
                <button class="btn btn-success" ${p.quantity == 0 ? "disabled" : ""}>
                    ${p.quantity == 0 ? "Out of Stock" : "Buy Now"}
                </button>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
