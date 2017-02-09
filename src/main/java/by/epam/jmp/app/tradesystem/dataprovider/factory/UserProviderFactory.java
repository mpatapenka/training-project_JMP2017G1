package by.epam.jmp.app.tradesystem.dataprovider.factory;

import by.epam.jmp.app.tradesystem.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.dataprovider.inmemory.IMUserProviderImpl;

public final class UserProviderFactory {

    private UserProviderFactory() { }

    public static UserProvider getUserProviderInstance() {
        return new IMUserProviderImpl();
    }

}