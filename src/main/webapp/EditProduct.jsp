<%@ page import="model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product product = (Product) request.getAttribute("product");
    if (product == null) {
%>
    <p class="text-center mt-5 text-danger">Product not found.</p>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css.css">
    <style>
        .edit-card {
            max-width: 500px;
            margin: 3rem auto;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            text-align: center;
        }

        .edit-card img {
            width: 100%;
            height: 200px;
            object-fit: contain;
            margin-bottom: 1rem;
        }

        .edit-card .price {
            color: #F96E46;
            font-weight: bold;
        }

        .edit-card form .form-group {
            text-align: left;
        }

        .edit-card .btn-group {
            display: flex;
            justify-content: center;
            gap: 1rem;
        }
    </style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Edit Product</h2>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/Product/edit" method="post">
                <input type="hidden" name="ProductID" value="<%= product.getProductID() %>" />

                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="Name" class="form-control" value="<%= product.getName() %>" required>
                </div>

                <div class="form-group">
                    <label>Image URL</label>
                    <input type="text" name="imageUrl" class="form-control" value="<%= product.getImageUrl() %>">
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea name="Description" class="form-control" rows="3"><%= product.getDescription() %></textarea>
                </div>

                <div class="form-group">
                    <label>Price</label>
                    <input type="number" step="0.01" name="Price" class="form-control" value="<%= product.getPrice() %>" required>
                </div>

                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="Stock" class="form-control" value="<%= product.getQuantity() %>" required>
                </div>

                <div class="form-check">
                    <input type="checkbox" name="favourited" class="form-check-input" id="favouriteCheck"
                        <%= product.isFavourited() ? "checked" : "" %>>
                    <label class="form-check-label" for="favouriteCheck">Mark as Favourite</label>
                </div>

                <div class="mt-4">
                    <button type="submit" class="btn btn-success">Update Product</button>
                    <a href="${pageContext.request.contextPath}/ManageProduct.jsp" class="btn btn-secondary">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
