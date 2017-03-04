package by.epam.jmp.app.tradesystem.core.service.impl;

import by.epam.jmp.app.tradesystem.core.context.ServicesHolder;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;
import by.epam.jmp.app.tradesystem.core.model.UserRole;
import by.epam.jmp.app.tradesystem.core.service.ProductService;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ProductServiceImplTest {

    private static final Logger LOG = Logger.getLogger(ProductServiceImplTest.class);
    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";
    private static final String TEST_NAME = "test_product";
    private static final String TEST_DESCRIPTION = "test_description";
    private static final BigDecimal TEST_COST = new BigDecimal("2000");

    private ProductService productService = ServicesHolder.getProductServiceInstance();
    private static User testVendor;

    @BeforeClass
    public static void beforeClass() {
        testVendor = new User(TEST_USERNAME, TEST_PASSWORD, UserRole.VENDOR);
    }

    @AfterClass
    public static void afterClass() {
        testVendor = null;
    }

    @Test
    public void testCreateProduct() {
        Product product = productService.createProduct(TEST_NAME, TEST_DESCRIPTION, TEST_COST, testVendor);
        assertNotNull("Product should be created.", product);
        assertEquals(TEST_NAME, product.getName());
        assertEquals(TEST_DESCRIPTION, product.getDescription());
        assertEquals(TEST_COST, product.getCost());
        assertEquals(testVendor, product.getVendor());
    }

    @Test
    public void testUpdateProduct() {
        Product product = productService.createProduct(TEST_NAME, TEST_DESCRIPTION, TEST_COST, testVendor);
        assertNotNull("Product should be created.", product);

        final String newName = "new_name";
        final String newDescription = "new_description";
        product.setName(newName);
        product.setDescription(newDescription);

        Product updatedProduct = productService.updateProduct(product);
        assertNotNull(updatedProduct);
        assertTrue("Product and updated product should have different references.", product != updatedProduct);
        assertEquals(newName, updatedProduct.getName());
        assertEquals(newDescription, updatedProduct.getDescription());
    }

    @Test
    public void testGetProductById() {
        Product product = productService.createProduct(TEST_NAME, TEST_DESCRIPTION, TEST_COST, testVendor);
        Product foundProduct = productService.getProduct(product.getId());
        assertEquals("Created and found products should be equals.", product, foundProduct);
        assertTrue("Products should not have same references.", product != foundProduct);
    }

    @Test
    public void testGetProducts() {
        final int minimalProductsCount = 4;
        final int exclusiveProductsCount = 1;
        final String testName1 = "test_name_1";
        final String testName2 = "test_name_2";
        final String testName3 = "test_name_3";
        final String testName4 = "test_name_4";
        final String testExclusiveVendorName = "exclusive_vendor";
        final User testExclusiveVendor = new User(testExclusiveVendorName, TEST_PASSWORD, UserRole.VENDOR);

        productService.createProduct(testName1, TEST_DESCRIPTION, TEST_COST, testVendor);
        productService.createProduct(testName2, TEST_DESCRIPTION, TEST_COST, testVendor);
        productService.createProduct(testName3, TEST_DESCRIPTION, TEST_COST, testExclusiveVendor);
        productService.createProduct(testName4, TEST_DESCRIPTION, TEST_COST, testVendor);

        List<Product> products = productService.getProducts();
        assertTrue(String.format("Count of created products should be bigger then '%d'", minimalProductsCount),
                products.size() >= minimalProductsCount);
        LOG.debug(products);

        products = productService.getProducts(testExclusiveVendorName);
        assertEquals(String.format("We have only '%d' exclusive vendor(s), and his(them) product(s)", exclusiveProductsCount),
                exclusiveProductsCount, products.size());
    }

    @Test
    public void testDeleteProduct() {
        final int initialCountOfProducts = productService.getProducts().size();

        Product product = productService.createProduct(TEST_NAME, TEST_DESCRIPTION, TEST_COST, testVendor);
        final int countOfProductsAfterAdd = productService.getProducts().size();
        assertTrue(countOfProductsAfterAdd == initialCountOfProducts + 1);

        productService.deleteProduct(product);
        final int countOfProductsAfterDelete = productService.getProducts().size();
        assertTrue(countOfProductsAfterDelete == initialCountOfProducts);
    }

}