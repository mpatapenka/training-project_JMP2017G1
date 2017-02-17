package by.epam.jmp.app.tradesystem.context;

import by.epam.jmp.app.tradesystem.service.ProductService;
import by.epam.jmp.app.tradesystem.service.UserService;
import by.epam.jmp.app.tradesystem.service.impl.ProductServiceImpl;
import by.epam.jmp.app.tradesystem.service.impl.UserServiceImpl;

public final class ServicesHolder {

    private static UserService userService;
    private static ProductService productService;

    private ServicesHolder() { }

    public static UserService getUserServiceInstance() {
        if (userService == null) {
            synchronized (ServicesHolder.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    public static ProductService getProductServiceInstance() {
        if (productService == null) {
            synchronized (ServicesHolder.class) {
                if (productService == null) {
                    productService = new ProductServiceImpl();
                }
            }
        }
        return productService;
    }

}