package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.UserRole;

public interface UserService {

    void login(String username, String password);

    void register(String username, String password, UserRole userRole);

}