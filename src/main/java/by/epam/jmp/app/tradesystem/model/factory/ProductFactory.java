package by.epam.jmp.app.tradesystem.model.factory;

import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.Vendor;

import java.math.BigDecimal;

public final class ProductFactory {

    private ProductFactory() { }

    public static Product getProduct(String name, String description, BigDecimal cost, Vendor vendor) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        product.setVendor(vendor);
        return product;
    }

}