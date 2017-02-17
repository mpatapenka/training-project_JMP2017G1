package by.epam.jmp.app.tradesystem.context;

import by.epam.jmp.app.tradesystem.dataprovider.*;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMOrderProviderImpl;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMPackageProviderImpl;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMProductProviderImpl;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMUserProviderImpl;
import by.epam.jmp.app.tradesystem.model.IdentifiedType;
import org.apache.log4j.Logger;

public final class DataProvidersHolder {

    private static final Logger LOG = Logger.getLogger(DataProvidersHolder.class);

    private static OrderProvider orderProvider;
    private static PackageProvider packageProvider;
    private static ProductProvider productProvider;
    private static UserProvider userProvider;

    private DataProvidersHolder() { }

    @SuppressWarnings("unchecked")
    private static <T extends IdentifiedType> GenericProvider<T> getInstance(GenericProvider<T> provider,
            Class<?> clazz) {
        if (provider == null) {
            synchronized (DataProvidersHolder.class) {
                if (provider == null) {
                    try {
                        provider = (GenericProvider<T>) clazz.newInstance();
                    } catch (Exception e) {
                        LOG.error(String.format("Exception during creating instance of class '%s'", clazz.getName()), e);
                        throw new RuntimeException("Error in provider holder.", e);
                    }
                }
            }
        }
        return provider;
    }

    public static OrderProvider getOrderProviderInstance() {
        return (OrderProvider) getInstance(orderProvider, IMOrderProviderImpl.class);
    }

    public static PackageProvider getPackageProviderInstance() {
        return (PackageProvider) getInstance(packageProvider, IMPackageProviderImpl.class);
    }

    public static ProductProvider getProductProviderInstance() {
        return (ProductProvider) getInstance(productProvider, IMProductProviderImpl.class);
    }

    public static UserProvider getUserProviderInstance() {
        return (UserProvider) getInstance(userProvider, IMUserProviderImpl.class);
    }

}