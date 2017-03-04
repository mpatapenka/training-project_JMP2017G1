package by.epam.jmp.app.tradesystem.core.dataprovider;

import by.epam.jmp.app.tradesystem.core.model.User;

public interface UserProvider extends GenericProvider<User> {

    /**
     * Find user by username
     *
     * @param username specified username
     * @return user object with specified username
     */
    User loadUserByUsername(String username);

}