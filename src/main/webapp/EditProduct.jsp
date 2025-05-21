<%@page import="com.IoTBay.Models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Products</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<main class="container mt-5">
    <h2 class="text-center mb-4">IoTBay Product Management</h2>

    <div class="row">
        <!-- Add Product Form -->
        <div class="col-md-6 mb-4">
            <form action="../Products/add" method="post" id="addProductform" class="border p-4 rounded bg-light">
                <h5 class="mb-3">Add Product</h5>
                <div class="form-group">
                    <label>Name</label>
                    <input class="form-control" type="text" name="Name" required />
                </div>
                <div class="form-group">
                    <label>Stock</label>
                    <input class="form-control" type="number" name="Stock" required />
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input class="form-control" type="text" name="Price" required />
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <textarea class="form-control" name="Description" rows="3" required></textarea>
                </div>
                <div class="form-group">
                    <label>Category ID</label>
                    <input class="form-control" type="text" name="CategoryID" required />
                </div>
                <button type="submit" class="btn btn-success">Add Product</button>
                <button type="reset" class="btn btn-danger">Reset</button>
            </form>
        </div>

        <!-- Delete Product Form -->
        <div class="col-md-6 mb-4">
            <form action="../Products/delete" method="post" id="deleteProductform" class="border p-4 rounded bg-light">
                <h5 class="mb-3">Delete Product</h5>
                <div class="form-group">
                    <label>Product ID</label>
                    <input class="form-control" type="text" name="ProductID" required />
                </div>
                <button type="submit" class="btn btn-danger">Delete Product</button>
            </form>
        </div>
    </div>

    <div class="mt-5">
        <h4 class="text-center">Product Inventory</h4>
        <%
            List<Product> products = (ArrayList<Product>) request.getAttribute("products");
        %>
        <% if (products == null || products.isEmpty()) { %>
            <h5 class="text-center mt-4">No Products Found.</h5>
        <% } else { %>
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Product ID</th>
                        <th>Name</th>
                        <th>Stock</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Category ID</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Product product : products) { %>
                    <tr>
                        <td><%= product.getProId() %></td>
                        <td><%= product.getName() %></td>
                        <td><%= product.getStock() %></td>
                        <td>$<%= product.getPrice() %></td>
                        <td><%= product.getDescription() %></td>
                        <td><%= product.getCatID() %></td>
                        <td>
                            <a href="../EditProduct.jsp?productID=<%= product.getProId() %>" class="btn btn-sm btn-success">Edit</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
        <% } %>
    </div>
</main>

</body>
</html>
