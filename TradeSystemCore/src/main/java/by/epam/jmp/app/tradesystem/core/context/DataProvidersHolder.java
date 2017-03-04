package by.epam.jmp.app.tradesystem.core.context;

import by.epam.jmp.app.tradesystem.core.dataprovider.GenericProvider;
import by.epam.jmp.app.tradesystem.core.dataprovider.OrderProvider;
import by.epam.jmp.app.tradesystem.core.dataprovider.PackageProvider;
import by.epam.jmp.app.tradesystem.core.dataprovider.ProductProvider;
import by.epam.jmp.app.tradesystem.core.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.core.dataprovider.inmemory.IMOrderProviderImpl;
import by.epam.jmp.app.tradesystem.core.dataprovider.inmemory.IMPackageProviderImpl;
import by.epam.jmp.app.tradesystem.core.dataprovider.inmemory.IMProductProviderImpl;
import by.epam.jmp.app.tradesystem.core.dataprovider.inmemory.IMUserProviderImpl;
import by.epam.jmp.app.tradesystem.core.model.IdentifiedType;
import org.apache.log4j.Logger;

public final class DataProvidersHolder {

    private static final Logger LOG = Logger.getLogger(DataProvidersHolder.class);

    private static volatile OrderProvider orderProvider;
    private static volatile PackageProvider packageProvider;
    private static volatile ProductProvider productProvider;
    private static volatile UserProvider userProvider;

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