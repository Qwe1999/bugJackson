package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JdbcDAO <T> {


    public abstract int insert(T object) throws SQLException;

    public abstract Optional<T> selectById(int id) throws SQLException;

    public abstract Optional<List<T>> selectAll() throws SQLException;



    public abstract void deleteById(int id) throws SQLException;
}
