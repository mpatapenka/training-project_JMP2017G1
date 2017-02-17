package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.context.DataProvidersHolder;
import by.epam.jmp.app.tradesystem.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductProvider productProvider = DataProvidersHolder.getProductProviderInstance();

    @Override
    public Product createProduct(String name, String description, BigDecimal cost, User vendor) {
        Product product = Product.buildProduct(name, description, cost, vendor);
        return productProvider.create(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productProvider.update(product);
    }

    @Override
    public Product getProduct(long id) {
        return productProvider.find(id);
    }

    @Override
    public List<Product> getProducts() {
        return productProvider.getAll();
    }

    @Override
    public List<Product> getProducts(String vendorUsername) {
        return productProvider.loadProductsByVendor(vendorUsername);
    }

    @Override
    public void deleteProduct(Product product) {
        productProvider.delete(product);
    }

}