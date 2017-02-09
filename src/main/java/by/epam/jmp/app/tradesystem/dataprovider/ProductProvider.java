package by.epam.jmp.app.tradesystem.dataprovider;

import by.epam.jmp.app.tradesystem.model.Product;

import java.util.List;

public interface ProductProvider {

    void addOrUpdate(Product product);

    List<Product> getAll();

}