package by.epam.jmp.app.tradesystem.service.impl;

import by.epam.jmp.app.tradesystem.context.DataProvidersHolder;
import by.epam.jmp.app.tradesystem.dataprovider.PackageProvider;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Package;
import by.epam.jmp.app.tradesystem.model.User;
import by.epam.jmp.app.tradesystem.service.PackageService;
import by.epam.jmp.app.tradesystem.util.DateUtil;

import java.sql.Date;
import java.util.List;

public class PackageServiceImpl implements PackageService {

    private final PackageProvider packageProvider = DataProvidersHolder.getPackageProviderInstance();

    @Override
    public Package createPackage(Order order, User delivery) {
        int daysForDelivery = 0;
        Package pack = Package.buildPackage(null, daysForDelivery, order, delivery);
        return packageProvider.create(pack);
    }

    @Override
    public Package sendPackage(Package pack) {
        synchronized (packageProvider) {
            Package actualPack = packageProvider.find(pack.getId());
            if (actualPack.isPendingSend()) {
                Date today = DateUtil.getTodaySqlDate();
                // Generate random delivery days count, it will be random value from 1 till 4 days for delivery
                int deliveryDays = (int) (Math.random() * 10 / 3 + 1);

                actualPack.setDepartureDate(today);
                actualPack.setDaysForDelivery(deliveryDays);
                actualPack.setPendingSend(false);
            }
            throw new IllegalArgumentException("Package already was sent.");
        }
    }

    @Override
    public List<Package> getPackages() {
        return packageProvider.getAll();
    }

}
