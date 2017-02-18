package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Package;
import by.epam.jmp.app.tradesystem.model.User;

import java.util.List;

public interface PackageService {

    Package createPackage(Order order, User delivery);

    Package sendPackage(Package pack);

    List<Package> getPackages();

}
