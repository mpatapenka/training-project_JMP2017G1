package by.epam.jmp.app.tradesystem.dataprovider;

import by.epam.jmp.app.tradesystem.model.Product;

import java.util.List;

public interface ProductProvider extends GenericProvider<Product> {

    /**
     * Find all products related with specified vendor
     *
     * @param vendorUsername username of vendor
     * @return List of products related with specified vendor
     */
    List<Product> loadProductsByVendor(String vendorUsername);

}