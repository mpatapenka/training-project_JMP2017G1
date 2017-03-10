package by.epam.jmp.app.tradesystem.core.facade.sqlite;

import by.epam.jmp.app.tradesystem.core.facade.DBInstance;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;
import by.epam.jmp.app.tradesystem.core.model.UserRole;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.List;

@RunWith(JUnit4.class)
public class SQLiteInstanceTest {

    private static final Logger LOG = Logger.getLogger(SQLiteInstanceTest.class);

    private static DBInstance facade = new SQLiteInstance();

    @BeforeClass
    public static void beforeClass() {
        facade.createSchema();
    }

    @AfterClass
    public static void afterClass() {
        facade.dropSchema();
    }

    @Test
    public void testSQLiteInstanceEntireCase() {
        final User welcomeVendor = new User("Welcome", "qwerty", UserRole.VENDOR);
        welcomeVendor.setId(777L);

        Product[] productsWelcomeVendor = {
                Product.buildProduct("IPhone", "Apple IPhone 8S 256Gb Red Fenix", new BigDecimal("1450"), welcomeVendor),
                Product.buildProduct("Google Pixel 3S", "Google Pixel 3S 128Gb Strongly Brown", new BigDecimal("1250"), welcomeVendor),
                Product.buildProduct("GeForce 1380 Titan", "ASUS GeForce 1380 GT Strix Titan", new BigDecimal("1980"), welcomeVendor)
        };

        for (Product product : productsWelcomeVendor) {
            facade.addProduct(product);
        }

        List<Product> products = facade.getAllProducts();
        Assert.assertTrue("Products should not be empty!", !products.isEmpty());

        for (Product product : products) {
            LOG.debug(product);
        }

        Assert.assertArrayEquals(productsWelcomeVendor, products.toArray());
    }

}