package by.epam.jmp.troubleshooting;

import by.epam.jmp.app.tradesystem.context.DataProvidersHolder;
import by.epam.jmp.app.tradesystem.dataprovider.OrderProvider;
import by.epam.jmp.app.tradesystem.dataprovider.PackageProvider;
import by.epam.jmp.app.tradesystem.model.FormOfPayment;
import by.epam.jmp.app.tradesystem.model.Order;
import by.epam.jmp.app.tradesystem.model.Package;
import by.epam.jmp.app.tradesystem.model.Product;
import by.epam.jmp.app.tradesystem.util.DateUtil;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public final class JMP7_3_Troubleshooting {

    private static final Logger LOG = Logger.getLogger(JMP7_3_Troubleshooting.class);

    private static final OrderProvider ORDERS = DataProvidersHolder.getOrderProviderInstance();
    private static final PackageProvider PACKAGES = DataProvidersHolder.getPackageProviderInstance();

    static {
        final Product testProduct = Product.buildProduct("testProduct", "testDescription", new BigDecimal("1500"), null);
        final Order order = Order.buildOrder(DateUtil.getTodaySqlDate(), testProduct, null, FormOfPayment.NONE);

        for (int i = 0; i < 5; i++) {
            ORDERS.create(order);
        }
    }

    public static void main(String[] args) {
        Thread delivery = new Thread(new DeliverySimulator());
        Thread vendor = new Thread(new VendorSimulator());

        delivery.start();
        vendor.start();
    }

    private JMP7_3_Troubleshooting() { }

    private static class DeliverySimulator implements Runnable {

        @Override
        public void run() {

            LOG.debug("Delivery starts his work.");

            while (true) {
                // Delivery tries to find new order, packages it, sends it to customer
                // Delivery is going to add new item to his queue, so he is block his queue

                LOG.debug("Delivery going to block PACKAGES.");

                synchronized (PACKAGES) {
                    Package pack = null;

                    LOG.debug("Delivery blocked PACKAGES and going to SLEEP 5 sec.");

                    // Then Delivery is going to find new order for packaging
                    // He is trying to find needed queue and spends 5 seconds
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        LOG.debug("Thread was interrupted during sleep.", e);
                        e.printStackTrace();
                    }

                    LOG.debug("Delivery slept 5 secs and going to block ORDERS.");

                    // Then he wants to block orders queue
                    synchronized (ORDERS) {

                        LOG.debug("Delivery blocked ORDERS and is going to manipulate with it.");

                        // If Delivery can't find any orders for packaging then he stops
                        List<Order> orders = ORDERS.getAll();
                        if (orders.size() == 0) {
                            LOG.debug("Delivery ends his work, because ORDERS queue is empty.");
                            return;
                        }
                        // Then delivery gets first order and pack it
                        Order order = orders.get(0);
                        pack = Package.buildPackage(null, 0, order, null);
                        // After packing he delete order from queue
                        ORDERS.delete(order);
                    }

                    LOG.debug("Delivery release ORDERS.");

                    // After, Delivery adds package to his queue
                    PACKAGES.create(pack);
                }

                LOG.debug("Delivery release PACKAGES.");
            }
        }

    }

    private static class VendorSimulator implements Runnable {

        @Override
        public void run() {

            LOG.debug("Vendor starts his work and going to block ORDERS.");

            // Vendor is going to update order information in his queue, so he is block his queue
            synchronized (ORDERS) {

                LOG.debug("Vendor blocked ORDERS and going to proceed with updating payment and SLEEP 5 sec.");

                // Vendor forget about pay for all his orders, and he is going to complete payment for all orders in his queue
                for (Order order : ORDERS.getAll()) {
                    order.setFormOfPayment(FormOfPayment.CREDITCARD);
                    ORDERS.update(order);
                }

                // After that Vendor find that some orders already was added for delivery and he going to update it
                // Vendor tries to find needed queue and spends 5 seconds
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    LOG.debug("Thread was interrupted during sleep.", e);
                    e.printStackTrace();
                }

                LOG.debug("Vendor slept 5 secs and going to block PACKAGES.");

                // Then he wants to block packages queue
                synchronized (PACKAGES) {

                    LOG.debug("Vendor blocked PACKAGES and is going to manipulate with it.");

                    // Then delivery gets each package and update it order
                    for (Package pack : PACKAGES.getAll()) {
                        pack.getOrder().setFormOfPayment(FormOfPayment.CREDITCARD);
                        PACKAGES.update(pack);
                    }
                }

                LOG.debug("Vendor release PACKAGES.");
            }

            LOG.debug("Vendor release ORDERS and ends his work.");
        }
    }

}