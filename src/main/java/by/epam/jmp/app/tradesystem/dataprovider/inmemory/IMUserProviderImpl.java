package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

import by.epam.jmp.app.tradesystem.dataprovider.UserProvider;
import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.model.factory.UserFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMUserProviderImpl extends AbstractIMProvider implements UserProvider {

    /**
     * Map assign username to User object
     */
    private static final Map<String, User> userHolder = new HashMap<String, User>();

    @Override
    public void addOrUpdate(User user) {
        if (!userHolder.containsKey(user.getUsername())) {
            user.setId(generateId());

            User persistedUser = UserFactory.getUser(user.getUsername(), user.getPassword(), user.getUserRole());
            persistedUser.setFirstName(user.getFirstName());
            persistedUser.setLastName(user.getLastName());
            persistedUser.setId(user.getId());
            userHolder.put(persistedUser.getUsername(), persistedUser);
        } else if (!user.isNew()) {
            User persistedUser = userHolder.get(user.getUsername());
            persistedUser.setFirstName(user.getFirstName());
            persistedUser.setLastName(user.getLastName());
        } else {
            throw new IllegalArgumentException("Can't add or update. User with username " + user.getUsername()
                    + " already exists.");
        }
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<User>(userHolder.values());
    }

}