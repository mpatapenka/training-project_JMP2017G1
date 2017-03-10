package by.epam.jmp.app.tradesystem.core.util;

import by.epam.jmp.app.tradesystem.core.model.FormOfPayment;
import by.epam.jmp.app.tradesystem.core.model.Order;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.model.User;
import by.epam.jmp.app.tradesystem.core.model.UserRole;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Util class for working with database
 */
public final class DBUtil {

    private static final Logger LOG = Logger.getLogger(DBUtil.class);

    /**
     * Util class cannot be instantiated
     */
    private DBUtil() { }

    /**
     * Util class for working with SQLite database
     */
    public static class SQLite {

        private static final String SQLITE_PROPERTIES = "sqlite.properties";
        private static final String SQLITE_DRIVER = "sqlite.driver";
        private static final String SQLITE_DB_URL = "sqlite.db.url";
        private static final String SQLITE_DB_NAME = "sqlite.db.name";
        private static final String SQLITE_DB_CONNECTION_MOD = "sqlite.db.connection.single";

        private static final Properties PROPERTIES = new Properties();
        private static Connection connection;

        static {
            InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(SQLITE_PROPERTIES);

            try {
                PROPERTIES.load(is);
                Class.forName(PROPERTIES.getProperty(SQLITE_DRIVER, "org.sqlite.JDBC"));
            } catch (IOException e) {
                LOG.error("Cannot load properties for SQLite, filename: " + SQLITE_PROPERTIES);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                LOG.error("Driver class not found.");
                e.printStackTrace();
            }
        }

        public static Connection connect() {
            final String path = PROPERTIES.getProperty(SQLITE_DB_URL, "jbdc:sqlite:");
            final String name = PROPERTIES.getProperty(SQLITE_DB_NAME, "mysqlite.db");
            final boolean singleMod = Boolean.parseBoolean(PROPERTIES.getProperty(SQLITE_DB_CONNECTION_MOD, "true"));
            return connect(path, name, singleMod);
        }

        public static Connection connect(String path, String name, boolean singleMod) {
            if (!singleMod) {
                return getConnection(path + name);
            }

            if (connection == null) {
                synchronized (DBUtil.SQLite.class) {
                    if (connection == null) {
                        connection = getConnection(path + name);
                    }
                }
            }
            return connection;
        }

        public static void createOrderTable(Connection connection) {
            final String query = "CREATE TABLE IF NOT EXISTS _ORDER " +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "ORDER_DATE INTEGER, " +
                    "PRODUCT_ID INTEGER, " +
                    "USER_ID INTEGER, " +
                    "FORM_OF_PAYMENT_CODE INTEGER);";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                LOG.error("Cannot create _ORDER table.", e);
                e.printStackTrace();
            }
        }

        public static void dropOrderTable(Connection connection) {
            final String query = "DROP TABLE IF EXISTS _ORDER;";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                LOG.error("Cannot drop _ORDER table.", e);
                e.printStackTrace();
            }
        }

        public static void createProductTable(Connection connection) {
            final String query = "CREATE TABLE IF NOT EXISTS PRODUCT " +
                    "(ID INTEGER PRIMARY KEY NOT NULL, " +
                    "NAME TEXT, " +
                    "DESCRIPTION TEXT, " +
                    "COST TEXT, " +
                    "VENDOR_ID INTEGER);";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                LOG.error("Cannot create PRODUCT table.", e);
                e.printStackTrace();
            }
        }

        public static void dropProductTable(Connection connection) {
            final String query = "DROP TABLE IF EXISTS PRODUCT;";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                LOG.error("Cannot drop PRODUCT table.", e);
                e.printStackTrace();
            }
        }

        public static void addOrder(Connection connection, Order order) {
            final String query = "INSERT INTO _ORDER (ORDER_DATE, PRODUCT_ID, USER_ID, FORM_OF_PAYMENT_CODE) " +
                    "VALUES (?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setDate(1, order.getOrderDate());
                preparedStatement.setLong(2, order.getProduct().getId());
                preparedStatement.setLong(3, order.getCustomer().getId());
                preparedStatement.setLong(4, order.getFormOfPayment().getId());

                preparedStatement.execute();
            } catch (SQLException e) {
                LOG.error("Cannot add new Order, order: " + order, e);
                e.printStackTrace();
            }
        }

        public static void addProduct(Connection connection, Product product) {
            final String query = "INSERT INTO PRODUCT (NAME, DESCRIPTION, COST, VENDOR_ID) " +
                    "VALUES (?, ?, ?, ?);";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setString(3, product.getCost().toString());
                preparedStatement.setLong(4, product.getVendor().getId());

                preparedStatement.execute();
            } catch (SQLException e) {
                LOG.error("Cannot add new Product, product: " + product, e);
                e.printStackTrace();
            }
        }

        public static List<Order> getAllOrders(Connection connection) {
            final String query = "SELECT * FROM _ORDER";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                List<Order> orders = new ArrayList<Order>();

                while (resultSet.next()) {
                    final long id = resultSet.getLong("ID");
                    final Date date = resultSet.getDate("ORDER_DATE");
                    final long productId = resultSet.getLong("PRODUCT_ID");
                    final long userId = resultSet.getLong("USER_ID");
                    final int fopCode = resultSet.getInt("FORM_OF_PAYMENT_CODE");

                    Product product = new Product();
                    product.setId(productId);

                    User customer = new User("", "", UserRole.CUSTOMER);
                    customer.setId(userId);

                    FormOfPayment fop = null;
                    switch (fopCode) {
                        case FormOfPayment.NONE_FOP_CODE:
                            fop = FormOfPayment.NONE;
                            break;
                        case FormOfPayment.CREDITCARD_FOP_CODE:
                            fop = FormOfPayment.CREDITCARD;
                            break;
                        case FormOfPayment.CASH_FOP_CODE:
                            fop = FormOfPayment.CASH;
                            break;
                        default:
                            LOG.error("Unsupported FOP code: " + fopCode);
                            throw new IllegalArgumentException();
                    }

                    Order order = Order.buildOrder(date, product, customer, fop);
                    order.setId(id);

                    orders.add(order);
                }

                return orders;
            } catch (SQLException e) {
                LOG.error("Cannot read orders.", e);
                throw new RuntimeException(e);
            }
        }

        public static List<Product> getAllProducts(Connection connection) {
            final String query = "SELECT * FROM PRODUCT";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                List<Product> products = new ArrayList<Product>();

                while (resultSet.next()) {
                    final long id = resultSet.getLong("ID");
                    final String name = resultSet.getString("NAME");
                    final String description = resultSet.getString("DESCRIPTION");
                    final BigDecimal cost = new BigDecimal(resultSet.getString("COST"));
                    final long vendorId = resultSet.getLong("VENDOR_ID");

                    User vendor = new User("Welcome", "qwerty", UserRole.VENDOR);
                    vendor.setId(vendorId);

                    Product product = Product.buildProduct(name, description, cost, vendor);
                    product.setId(id);

                    products.add(product);
                }

                return products;
            } catch (SQLException e) {
                LOG.error("Cannot read orders.", e);
                throw new RuntimeException(e);
            }
        }

        private static Connection getConnection(String url) {
            try {
                return DriverManager.getConnection(url);
            } catch (Exception e) {
                LOG.error("Cannot create connection to SQLite database, URL: " + url);
                throw new RuntimeException(e);
            }
        }

    }

}