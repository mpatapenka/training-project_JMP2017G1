package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

import by.epam.jmp.app.tradesystem.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.model.Product;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

public class IMProductProviderImpl extends IMGenericProviderImpl<Product> implements ProductProvider {

    @Override
    public List<Product> loadProductsByVendor(String vendorUsername) {
        List<Product> productsOfVendor = new ArrayList<Product>();
        synchronized (this) {
            for (Product product : getAll()) {
                if (product.getVendor() != null && vendorUsername.equals(product.getVendor().getUsername())) {
                    productsOfVendor.add(SerializationUtils.clone(product));
                }
            }
        }
        return productsOfVendor;
    }

}