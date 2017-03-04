package by.epam.jmp.app.tradesystem.core.service;

import by.epam.jmp.app.tradesystem.core.model.FormOfPayment;
import by.epam.jmp.app.tradesystem.core.model.Order;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(Product product, User customer);

    Order payForOrder(Order order, FormOfPayment fop);

    Order updateOrder(Order order);

    List<Order> getOrders();

}
