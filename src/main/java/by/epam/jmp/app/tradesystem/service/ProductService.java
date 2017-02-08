package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.Vendor;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Create new product
     *
     * @param name        name of product
     * @param description description of product
     * @param cost        cost of product
     * @param vendor      Vendor object, who sells this product
     * @return new Product object
     */
    Product createProduct(String name, String description, BigDecimal cost, Vendor vendor);

    /**
     * Get product by ID
     *
     * @param id ID of product
     * @return specified by ID product
     */
    Product getProduct(long id);

    /**
     * Get all products
     *
     * @return List of products
     */
    List<Product> getProducts();

    /**
     * Get all products of specified vendor
     *
     * @param vendorUsername vendor username
     * @return List of Products for specified vendor
     */
    List<Product> getProducts(String vendorUsername);

}