package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.model.Customer;
import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.model.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserServiceImplTest {

    private UserServiceImpl userService;

    private static final String TEST_USERNAME = "test_username";
    private static final String TEST_PASSWORD = "test_password";

    @Before
    public void before() {
        userService = new UserServiceImpl();
    }

    @After
    public void after() {
        userService = null;
    }

    @Test
    public void testService() {
        User user = userService.register(TEST_USERNAME, TEST_PASSWORD, UserRole.CUSTOMER);
        assertNotNull(user);
        assertTrue(user instanceof Customer);
        assertEquals(TEST_USERNAME, user.getUsername());
        assertEquals(TEST_PASSWORD, user.getPassword());

        final String firstName = "FirstName";
        final String lastName = "LastName";
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userService.updateUserDetails(user);

        List<User> users = userService.listAllUsers();
        assertEquals(1, users.size());

        user = users.get(0);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
    }

}
