package by.epam.jmp.app.tradesystem.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final List<Order> orders = new ArrayList<Order>();

    public Customer(String username, UserRole userRole) {
        super(username, userRole);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Customer{} " + super.toString();
    }

}