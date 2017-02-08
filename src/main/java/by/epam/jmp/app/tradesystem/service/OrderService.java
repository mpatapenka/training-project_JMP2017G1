package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Customer;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Product;

import java.sql.Date;
import java.util.List;

public interface OrderService {

    /**
     * Create new order
     *
     * @param product  Product which customer want to buy
     * @param customer Customer object
     * @return new Order object
     */
    Order createOrder(Product product, Customer customer);

    /**
     * Get order by ID
     *
     * @param id ID of order
     * @return specified by ID order
     */
    Order getOrder(long id);

    /**
     * Get all orders for specified customer
     *
     * @param customerUsername customer username
     * @return List of orders for specified customer
     */
    List<Order> getOrders(String customerUsername);

    /**
     * Get all orders by specified date
     *
     * @param date specified date of Order
     * @return List of orders for specified date
     */
    List<Order> getOrders(Date date);

    /**
     * Get all orders for specified date range
     *
     * @param startDate date of start period
     * @param endDate   date of end period
     * @return List of orders for specified date range
     */
    List<Order> getOrders(Date startDate, Date endDate);

}