package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

import by.epam.jmp.app.tradesystem.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.model.factory.ProductFactory;

import java.util.*;

public class IMProductProviderImpl extends AbstractIMProvider implements ProductProvider {

    private final Map<Product, Product> productHolder = new HashMap<Product, Product>();

    @Override
    public void addOrUpdate(Product product) {
        if (product.isNew()) {
            product.setId(generateId());

            Product persistedProduct = ProductFactory.getProduct(product.getName(), product.getDescription(),
                    product.getCost(), product.getVendor());
            persistedProduct.setId(product.getId());
            productHolder.put(product, product);
        } else {
            Product persistedProduct = productHolder.get(product);
            persistedProduct.setCost(product.getCost());
            persistedProduct.setDescription(product.getDescription());
            persistedProduct.setName(product.getName());
        }
    }

    @Override
    public List<Product> getAll() {
        return new ArrayList<Product>(productHolder.values());
    }

}