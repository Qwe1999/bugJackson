package database;

import model.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcDAO <T> {

    protected Connection connection;

    public JdbcDAO() {

    }

    public JdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public JdbcDAO<T> setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    abstract int insert(T object) throws SQLException;

    abstract T selectById(int id) throws SQLException;

    abstract List<T> selectAll() throws SQLException;

    abstract void dropTable() throws SQLException;

    abstract void deleteById(int id) throws SQLException;
}
