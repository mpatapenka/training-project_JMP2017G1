package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class ProductServiceImplTest {

    private ProductService productService;

    private static final String TEST_NAME = "test_product";
    private static final String TEST_DESCRIPTION = "test_description";
    private static final BigDecimal TEST_COST = new BigDecimal("2000");

    @Before
    public void before() {
        productService = new ProductServiceImpl();
    }

    @After
    public void after() {
        productService = null;
    }

    @Test
    public void testService() {
        Product product = productService.createProduct(TEST_NAME, TEST_DESCRIPTION, TEST_COST, null);
        assertNotNull(product);
        assertEquals(TEST_NAME, product.getName());
        assertEquals(TEST_DESCRIPTION, product.getDescription());
        assertEquals(TEST_COST, product.getCost());

        final String newName = "new_name";
        product.setName(newName);
        productService.updateProduct(product);

        List<Product> products = productService.getProducts();
        assertEquals(1, products.size());

        product = products.get(0);
        assertEquals(newName, product.getName());
    }

}