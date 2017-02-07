package by.epam.jmp.app.tradesystem.service;

import by.epam.jmp.app.tradesystem.model.Deliveryman;
import by.epam.jmp.app.tradesystem.model.Package;

import java.util.List;

public interface DeliveryService {

    List<Package> getAllPackages();

    Package getPackageById(long id);

    List<Package> getDeliverymanPackages(Deliveryman deliveryman);

}