package test.java.model;

import main.java.model.ProductCatalog;
import main.java.model.dao.DBConnector;
import main.java.model.dao.ProductDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductDAOTest {

    private ProductDAO productDAO;

    @Before
    public void setup() {
        try {
            Connection conn = DBConnector.getConnection();
            productDAO = new ProductDAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            Assert.fail("Failed to connect to DB.");
        }
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setName("Asus Laptop");
        product.setImageUrl("images/asus.jpg");
        product.setDescription("Asus laptop with NVidia Graphics Card");
        product.setPrice(999.00);
        product.setQuantity(1);
        product.setFavourited(false);

        productDAO.add(product);

        // Retrieve last added product (not ideal for concurrency but okay for test)
        List<Product> all = productDAO.getAllProducts();
        Product last = all.get(all.size() - 1);

        Assert.assertEquals("Asus Laptop", last.getName());
        Assert.assertEquals("Asus laptop with NVidia Graphics Card", last.getDescription());
    }

    @Test
    public void testDeleteProduct() {
        // Add a product to delete
        Product product = new Product();
        product.setName("Temp Delete");
        product.setImageUrl("images/temp.jpg");
        product.setDescription("Temporary product for delete test");
        product.setPrice(50.0);
        product.setQuantity(1);
        product.setFavourited(false);

        productDAO.add(product);
        List<Product> all = productDAO.getAllProducts();
        Product last = all.get(all.size() - 1);

        productDAO.delete(last.getProductID());

        Product check = productDAO.findProductById(last.getProductID());
        Assert.assertNull(check);
    }

    @Test
    public void testGetSingleProduct() {
        List<Product> all = productDAO.getAllProducts();
        if (!all.isEmpty()) {
            Product any = all.get(0);
            Product found = productDAO.findProductById(any.getProductID());
            Assert.assertNotNull(found);
            Assert.assertEquals(any.getProductID(), found.getProductID());
        } else {
            Assert.fail("No products found to test.");
        }
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productDAO.getAllProducts();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.size() >= 0);
    }
}
