package by.epam.jmp.app.tradesystem.model;

import java.util.ArrayList;
import java.util.List;

public class Vendor extends UserCompany {

    private final List<Product> products = new ArrayList<Product>();

    public Vendor(String username, String password) {
        super(username, password, UserRole.VENDOR);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Vendor{} " + super.toString();
    }

}