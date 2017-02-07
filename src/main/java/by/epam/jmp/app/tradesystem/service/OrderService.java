package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Customer;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Product;

import java.util.List;

public interface OrderService {

    void createOrder(Product product, Customer customer);

    List<Order> getCustomerOrders(Customer customer);

}