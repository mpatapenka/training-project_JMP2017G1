package by.epam.jmp.app.tradesystem.core.service.impl;

import by.epam.jmp.app.tradesystem.core.context.DataProvidersHolder;
import by.epam.jmp.app.tradesystem.core.dataprovider.OrderProvider;
import by.epam.jmp.app.tradesystem.core.model.FormOfPayment;
import by.epam.jmp.app.tradesystem.core.model.Order;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;
import by.epam.jmp.app.tradesystem.core.service.OrderService;
import by.epam.jmp.app.tradesystem.core.util.DateUtil;

import java.sql.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderProvider orderProvider = DataProvidersHolder.getOrderProviderInstance();

    @Override
    public Order createOrder(Product product, User customer) {
        Date today = DateUtil.getTodaySqlDate();
        FormOfPayment notPayedFop = FormOfPayment.NONE;
        Order order = Order.buildOrder(today, product, customer, notPayedFop);
        return orderProvider.create(order);
    }

    @Override
    public Order payForOrder(Order order, FormOfPayment fop) {
        if (fop.getCode() != FormOfPayment.NONE.getCode()) {
            synchronized (orderProvider) {
                Order orderForPay = orderProvider.find(order.getId());
                if (orderForPay.getFormOfPayment().getCode() != FormOfPayment.NONE.getCode()) {
                    throw new IllegalArgumentException(String.format("Order '#%d' already paid.", orderForPay.getId()));
                }
                orderForPay.setFormOfPayment(fop);
                return orderProvider.update(orderForPay);
            }
        }
        throw new IllegalArgumentException(String.format("Can't pay for order with provided form of payment: '%s'", fop.getName()));
    }

    @Override
    public Order updateOrder(Order order) {
        return orderProvider.update(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderProvider.getAll();
    }

}
