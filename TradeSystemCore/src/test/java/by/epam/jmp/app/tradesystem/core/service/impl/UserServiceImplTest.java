package by.epam.jmp.app.tradesystem.core.service.impl;

import by.epam.jmp.app.tradesystem.core.context.ServicesHolder;
import by.epam.jmp.app.tradesystem.core.model.User;
import by.epam.jmp.app.tradesystem.core.model.UserRole;
import by.epam.jmp.app.tradesystem.core.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserServiceImplTest {

    private static final Logger LOG = Logger.getLogger(UserServiceImplTest.class);
    private static final String TEST_PASSWORD = "test_password";

    private UserService userService = ServicesHolder.getUserServiceInstance();;

    @Test
    public void testRegister() {
        final String testUsername = "register_test_username";

        User user = userService.register(testUsername, TEST_PASSWORD, UserRole.CUSTOMER);
        assertNotNull("User should be created.", user);
        assertEquals(testUsername, user.getUsername());
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertEquals(UserRole.CUSTOMER, user.getUserRole());

        user = userService.register(testUsername, TEST_PASSWORD, UserRole.ADMIN);
        assertNull("We can't register one more user with same username.", user);
    }

    @Test
    public void testLogin() {
        final String testUsername = "login_test_username";
        final String testFailUsername = "fail_login_test_username";
        final String testFailPassword = "fail_test_password";

        User user = userService.register(testUsername, TEST_PASSWORD, UserRole.CUSTOMER);
        assertNotNull("User should be created.", user);

        user = userService.login(testFailUsername, TEST_PASSWORD);
        assertNull("User can't login with incorrect username.", user);

        user = userService.login(testUsername, testFailPassword);
        assertNull("User can't login with wrong password.", user);

        user = userService.login(testUsername, TEST_PASSWORD);
        assertNotNull("User should be logged in.", user);
        assertEquals(UserRole.CUSTOMER, user.getUserRole());
    }

    @Test
    public void testListAllUsers() {
        final int minimalUsersCount = 4;
        final String testUsername1 = "test_username_1";
        final String testUsername2 = "test_username_2";
        final String testUsername3 = "test_username_3";
        final String testUsername4 = "test_username_4";

        userService.register(testUsername1, TEST_PASSWORD, UserRole.CUSTOMER);
        userService.register(testUsername2, TEST_PASSWORD, UserRole.ADMIN);
        userService.register(testUsername3, TEST_PASSWORD, UserRole.VENDOR);
        userService.register(testUsername4, TEST_PASSWORD, UserRole.DELIVERY);

        List<User> users = userService.listAllUsers();
        assertTrue(String.format("Count of registered users should be bigger then '%d'", minimalUsersCount),
                users.size() >= minimalUsersCount);
        LOG.debug(users);
    }

}
