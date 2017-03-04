package by.epam.jmp.app.tradesystem.core.service;

import by.epam.jmp.app.tradesystem.core.model.Order;
import by.epam.jmp.app.tradesystem.core.model.Package;
import by.epam.jmp.app.tradesystem.core.model.User;

import java.util.List;

public interface PackageService {

    Package createPackage(Order order, User delivery);

    Package sendPackage(Package pack);

    List<Package> getPackages();

}
