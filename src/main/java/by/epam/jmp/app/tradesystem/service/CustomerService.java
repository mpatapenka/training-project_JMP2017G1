package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Customer;
import by.epam.jmp.app.tradesystem.model.Package;
import by.epam.jmp.app.tradesystem.model.Product;

import java.util.List;

public interface CustomerService {

    List<Product> getAllProducts();

    Product getProductById(long id);

    Product getProductByName(String name);

    List<Package> getCustomerPackages(Customer customer);

}