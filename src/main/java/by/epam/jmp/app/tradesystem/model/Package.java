package by.epam.jmp.app.tradesystem.model;

import java.sql.Date;

public class Package extends IdentifiedType {

    private Date departureDate;
    private int daysForDelivery;
    private Order order;
    private User delivery;
    private boolean isPendingSend = true;

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getDaysForDelivery() {
        return daysForDelivery;
    }

    public void setDaysForDelivery(int daysForDelivery) {
        this.daysForDelivery = daysForDelivery;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getDelivery() {
        return delivery;
    }

    public void setDelivery(User delivery) {
        this.delivery = delivery;
    }

    public boolean isPendingSend() {
        return isPendingSend;
    }

    public void setPendingSend(boolean pendingSend) {
        isPendingSend = pendingSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (daysForDelivery != aPackage.daysForDelivery) return false;
        if (isPendingSend != aPackage.isPendingSend) return false;
        if (departureDate != null ? !departureDate.equals(aPackage.departureDate) : aPackage.departureDate != null)
            return false;
        if (order != null ? !order.equals(aPackage.order) : aPackage.order != null) return false;
        return delivery != null ? delivery.equals(aPackage.delivery) : aPackage.delivery == null;
    }

    @Override
    public String toString() {
        return "Package{" +
                "departureDate=" + departureDate +
                ", daysForDelivery=" + daysForDelivery +
                ", order=" + order +
                ", delivery=" + delivery +
                ", isPendingSend=" + isPendingSend +
                "} " + super.toString();
    }

    public static Package buildPackage(Date departureDate, int daysForDelivery, Order order, User delivery) {
        Package pack = new Package();
        pack.setDepartureDate(departureDate);
        pack.setDaysForDelivery(daysForDelivery);
        pack.setOrder(order);
        pack.setDelivery(delivery);
        return pack;
    }

}