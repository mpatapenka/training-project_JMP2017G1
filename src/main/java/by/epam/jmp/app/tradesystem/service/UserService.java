package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.model.UserRole;

import java.util.List;

public interface UserService {

    /**
     * Perform login procedure
     *
     * @param username username
     * @param password password
     * @return User object associated with existing user
     */
    User login(String username, String password);

    /**
     * Perform register procedure
     *
     * @param username username
     * @param password password
     * @param userRole role of new user
     * @return User object which was created during register procedure
     */
    User register(String username, String password, UserRole userRole);

    /**
     * Get all users as list
     *
     * @return List of all users
     */
    List<User> listAllUsers();

}