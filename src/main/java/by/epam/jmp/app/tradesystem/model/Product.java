package by.epam.jmp.app.tradesystem.model;

import java.math.BigDecimal;

public class Product extends IdentifiedType {

    private String name;
    private String description;
    private BigDecimal cost;
    private Vendor vendor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        if (cost.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("Cost can't be less then zero.");
        }
        this.cost = cost;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (cost != null ? !cost.equals(product.cost) : product.cost != null) return false;
        return vendor != null ? vendor.equals(product.vendor) : product.vendor == null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", vendor=" + vendor +
                "} " + super.toString();
    }

}