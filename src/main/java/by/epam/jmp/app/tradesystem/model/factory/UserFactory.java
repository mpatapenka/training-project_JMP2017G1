package by.epam.jmp.app.tradesystem.model.factory;

import by.epam.jmp.app.tradesystem.model.*;

public final class UserFactory {

    private UserFactory() { }

    public static User getUser(String username, String password, UserRole userRole) {
        User user = null;
        switch (userRole.getCode()) {
            case UserRole.CUSTOMER_CODE:
                user = new Customer(username, password);
                break;
            case UserRole.VENDOR_CODE:
                user = new Vendor(username, password);
                break;
            case UserRole.DELIVERY_CODE:
                user = new Delivery(username, password);
                break;
            default:
                throw new IllegalArgumentException("Unsupported user role: " + userRole);
        }
        return user;
    }

}