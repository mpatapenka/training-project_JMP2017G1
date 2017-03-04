package by.epam.jmp.app.tradesystem.core.context;

import by.epam.jmp.app.tradesystem.core.service.OrderService;
import by.epam.jmp.app.tradesystem.core.service.PackageService;
import by.epam.jmp.app.tradesystem.core.service.ProductService;
import by.epam.jmp.app.tradesystem.core.service.UserService;
import by.epam.jmp.app.tradesystem.core.service.impl.OrderServiceImpl;
import by.epam.jmp.app.tradesystem.core.service.impl.PackageServiceImpl;
import by.epam.jmp.app.tradesystem.core.service.impl.ProductServiceImpl;
import by.epam.jmp.app.tradesystem.core.service.impl.UserServiceImpl;

public final class ServicesHolder {

    private static volatile UserService userService;
    private static volatile ProductService productService;
    private static volatile OrderService orderService;
    private static volatile PackageService packageService;

    private ServicesHolder() { }

    public static UserService getUserServiceInstance() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    public static ProductService getProductServiceInstance() {
        if (productService == null) {
            synchronized (ProductService.class) {
                if (productService == null) {
                    productService = new ProductServiceImpl();
                }
            }
        }
        return productService;
    }

    public static OrderService getOrderServiceInstance() {
        if (orderService == null) {
            synchronized (OrderService.class) {
                if (orderService == null) {
                    orderService = new OrderServiceImpl();
                }
            }
        }
        return orderService;
    }

    public static PackageService getPackageServiceInstance() {
        if (packageService == null) {
            synchronized (PackageService.class) {
                if (packageService == null) {
                    packageService = new PackageServiceImpl();
                }
            }
        }
        return packageService;
    }

}