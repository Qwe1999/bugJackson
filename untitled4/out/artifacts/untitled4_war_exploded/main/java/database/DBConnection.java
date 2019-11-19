package database;

import exceptions.TeacherException;
import model.Teacher;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBConnection  {
    static Connection connection ;

    static final String HOST = "jdbc:postgresql://localhost/schedule?user=postgres&password=1234&useUnicode=yes";

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(HOST);
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }


}
