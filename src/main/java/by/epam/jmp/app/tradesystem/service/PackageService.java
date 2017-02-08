package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Delivery;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Package;

import java.sql.Date;
import java.util.List;

public interface PackageService {

    /**
     * Create new package
     *
     * @param order    Order which customer want deliver
     * @param delivery Delivery object
     * @return new Package object
     */
    Package createPackage(Order order, Delivery delivery);

    /**
     * Get package by ID
     *
     * @param id ID of package
     * @return specified by ID package
     */
    Package getPackage(long id);

    /**
     * Get all orders for specified delivery
     *
     * @param deliveryUsername delivery username
     * @param isPendingSend    flag which determine what kind of package need to return, if true then return packages
     *                         which not processed yet, if false - otherwise
     * @return List of packages for specified delivery
     */
    List<Package> getPackages(String deliveryUsername, boolean isPendingSend);

    /**
     * Get all packages by specified date
     *
     * @param date          specified date of Package
     * @param isPendingSend flag which determine what kind of package need to return, if true then return packages
     *                      which not processed yet, if false - otherwise
     * @return List of packages for specified date
     */
    List<Package> getPackages(Date date, boolean isPendingSend);

    /**
     * Get all packages for specified date range
     *
     * @param startDate     date of start period
     * @param endDate       date of end period
     * @param isPendingSend flag which determine what kind of package need to return, if true then return packages
     *                      which not processed yet, if false - otherwise
     * @return List of packages for specified date range
     */
    List<Package> getPackages(Date startDate, Date endDate, boolean isPendingSend);

}