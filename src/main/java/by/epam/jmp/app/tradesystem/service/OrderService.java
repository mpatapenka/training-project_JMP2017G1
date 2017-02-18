package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.FormOfPayment;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(Product product, User customer);

    Order payForOrder(Order order, FormOfPayment fop);

    Order updateOrder(Order order);

    List<Order> getOrders();

}
