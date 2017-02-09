package by.epam.jmp.app.tradesystem.dataprovider.factory;

import by.epam.jmp.app.tradesystem.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMProductProviderImpl;

public final class ProductProviderFactory {

    private ProductProviderFactory() { }

    public static ProductProvider getProductProviderInstance() {
        return new IMProductProviderImpl();
    }

}