package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.dataprovider.factory.UserProviderFactory;
import by.epam.jmp.app.tradesystem.model.*;
import by.epam.jmp.app.tradesystem.model.factory.UserFactory;
import by.epam.jmp.app.tradesystem.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserProvider userProvider = UserProviderFactory.getUserProviderInstance();

    @Override
    public User login(String username, String password) {
        // TODO
        return null;
    }

    @Override
    public User register(String username, String password, UserRole userRole) {
        User user = UserFactory.getUser(username, password, userRole);
        userProvider.addOrUpdate(user);
        return user;
    }

    @Override
    public User updateUserDetails(User user) {
        userProvider.addOrUpdate(user);
        return user;
    }

    @Override
    public List<User> listAllUsers() {
        return userProvider.getAll();
    }

}