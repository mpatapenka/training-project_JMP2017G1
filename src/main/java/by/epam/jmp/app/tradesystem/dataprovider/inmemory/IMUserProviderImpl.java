package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

import by.epam.jmp.app.tradesystem.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.model.User;
import org.apache.commons.lang3.SerializationUtils;

public class IMUserProviderImpl extends IMGenericProviderImpl<User> implements UserProvider {

    @Override
    public User loadUserByUsername(String username) {
        synchronized (this) {
            for (User user : getAll()) {
                if (username.equals(user.getUsername())) {
                    return SerializationUtils.clone(user);
                }
            }
        }
        return null;
    }

}