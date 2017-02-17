package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.context.DataProvidersHolder;
import by.epam.jmp.app.tradesystem.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.model.UserRole;
import by.epam.jmp.app.tradesystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserProvider userProvider = DataProvidersHolder.getUserProviderInstance();

    @Override
    public User login(String username, String password) {
        for (User user : userProvider.getAll()) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User register(String username, String password, UserRole userRole) {
        User existingUser = userProvider.loadUserByUsername(username);
        if (existingUser == null) {
            User user = new User(username, password, userRole);
            return userProvider.create(user);
        }
        return null;
    }

    @Override
    public List<User> listAllUsers() {
        return userProvider.getAll();
    }

}