package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Customer;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Product;

import java.math.BigDecimal;

public interface VendorService {

    Product createProduct(String name, String description, BigDecimal cost);

    void createPackage(Order order, Customer customer);

}