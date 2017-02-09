package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.dataprovider.factory.ProductProviderFactory;
import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.Vendor;
import by.epam.jmp.app.tradesystem.model.factory.ProductFactory;
import by.epam.jmp.app.tradesystem.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider = ProductProviderFactory.getProductProviderInstance();

    @Override
    public Product createProduct(String name, String description, BigDecimal cost, Vendor vendor) {
        Product product = ProductFactory.getProduct(name, description, cost, vendor);
        productProvider.addOrUpdate(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        productProvider.addOrUpdate(product);
        return product;
    }

    @Override
    public Product getProduct(long id) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return productProvider.getAll();
    }

    @Override
    public List<Product> getProducts(String vendorUsername) {
        return null;
    }

}