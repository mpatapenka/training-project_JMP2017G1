package by.epam.jmp.app.tradesystem.core.facade;

import by.epam.jmp.app.tradesystem.core.model.Product;

import java.util.List;

public interface DBInstance {

    void createSchema();

    void dropSchema();

    void addProduct(Product product);

    List<Product> getAllProducts();

}