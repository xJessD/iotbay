<%@page import="com.IoTBay.Models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css.css"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container mt-5">
    <h2 class="text-center mb-4">Product Management</h2>

    <div class="row mb-4">
        <div class="col-md-6">
            <form action="../products/add" method="post" id="addProductform" class="border p-4 rounded bg-light">
                <h5>Add Product</h5>
                <div class="form-group">
                    <label>Name</label>
                    <input class="form-control" type="text" name="Name" required />
                </div>
                <div class="form-group">
                    <label>Stock</label>
                    <input class="form-control" type="text" name="Stock" required />
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input class="form-control" type="text" name="Price" required />
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input class="form-control" type="text" name="Description" style="min-height: 100px;" required />
                </div>
                <div class="form-group">
                    <label>Product Category</label>
                    <input class="form-control" type="text" name="CategoryID" required />
                </div>
                <button type="submit" class="btn btn-success">Add Product</button>
                <button type="reset" class="btn btn-danger">Reset</button>
            </form>
        </div>
        <div class="col-md-6">
            <form action="../products/delete" method="post" id="deleteProductform" class="border p-4 rounded bg-light">
                <h5>Delete Product</h5>
                <div class="form-group">
                    <label>Product ID</label>
                    <input class="form-control" type="text" name="ProductID" required />
                </div>
                <button type="submit" class="btn btn-danger">Delete Product</button>
            </form>
        </div>
    </div>

    <% List<Product> products = (ArrayList<Product>) request.getAttribute("products"); %>

    <% if (products == null || products.isEmpty()) { %>
        <h4 class="text-center">No Products Found.</h4>
    <% } else { %>
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">ProductID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Price</th>
                    <th scope="col">Description</th>
                    <th scope="col">CategoryID</th>
                    <th scope="col">Edit</th>
                </tr>
            </thead>
            <tbody>
                <% for(Product product : products) { %>
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
    <% } %>
</div>

</body>
</html>
