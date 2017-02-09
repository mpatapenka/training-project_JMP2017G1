package by.epam.jmp.app.tradesystem.dataprovider;

import by.epam.jmp.app.tradesystem.model.User;

import java.util.List;

/**
 * Common logic for providers might be extracted to GenericProvider (like as #addOrUpdate, #getAll methods)
 */
public interface UserProvider {

    void addOrUpdate(User user);

    List<User> getAll();

}