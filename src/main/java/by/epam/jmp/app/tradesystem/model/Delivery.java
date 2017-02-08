package by.epam.jmp.app.tradesystem.model;

import java.util.ArrayList;
import java.util.List;

public class Delivery extends UserCompany {

    private final List<Package> packages = new ArrayList<Package>();

    public Delivery(String username, UserRole userRole) {
        super(username, userRole);
    }

    public List<Package> getPackages() {
        return packages;
    }

    @Override
    public String toString() {
        return "Delivery{} " + super.toString();
    }

}