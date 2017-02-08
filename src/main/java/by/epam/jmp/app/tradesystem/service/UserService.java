package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.model.UserRole;

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
     * Update information of existing user
     *
     * @param user User object with updated information
     * @return Updated User object
     */
    User updateUserDetails(User user);

}