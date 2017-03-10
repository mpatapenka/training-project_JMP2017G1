package by.epam.jmp.app.tradesystem.core.facade.sqlite;

import by.epam.jmp.app.tradesystem.core.facade.DBInstance;
import by.epam.jmp.app.tradesystem.core.model.Product;
import by.epam.jmp.app.tradesystem.core.util.DBUtil;

import java.sql.Connection;
import java.util.List;

public class SQLiteInstance implements DBInstance {

    @Override
    public void createSchema() {
        Connection connection = DBUtil.SQLite.connect();
        DBUtil.SQLite.createOrderTable(connection);
        DBUtil.SQLite.createProductTable(connection);
    }

    @Override
    public void dropSchema() {
        Connection connection = DBUtil.SQLite.connect();
        DBUtil.SQLite.dropOrderTable(connection);
        DBUtil.SQLite.dropProductTable(connection);
    }

    @Override
    public void addProduct(Product product) {
        Connection connection = DBUtil.SQLite.connect();
        DBUtil.SQLite.addProduct(connection, product);
    }

    @Override
    public List<Product> getAllProducts() {
        Connection connection = DBUtil.SQLite.connect();
        return DBUtil.SQLite.getAllProducts(connection);
    }

}