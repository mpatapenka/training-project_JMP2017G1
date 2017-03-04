package by.epam.jmp.app.tradesystem.core.service;

import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Create new product
     *
     * @param name        name of product
     * @param description description of product
     * @param cost        cost of product
     * @param vendor      User object, who sells this product
     * @return new Product object
     */
    Product createProduct(String name, String description, BigDecimal cost, User vendor);

    /**
     * Update product with new values
     *
     * @param product Product object with new values
     * @return Product object with new values
     */
    Product updateProduct(Product product);

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

    /**
     * Delete product
     *
     * @param product specified product
     */
    void deleteProduct(Product product);

}