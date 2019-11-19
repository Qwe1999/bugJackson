package database;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {
    private static final Logger log = Logger.getLogger(DBConnection.class);
    private static Connection connection;


    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
//        try (FileInputStream fis =
////                     new FileInputStream("src/main/resources/config.properties")) {
////            Properties property = new Properties();
////            property.load(fis);
////            log.info("Call get connection");
////            connection = DriverManager.getConnection(property.getProperty("db.url"));
////            log.info("Got connection");
////            return connection;
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////   return null;
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/schedule?user=postgres&password=1234");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        log.info("Call close connection");
        connection.close();
        log.info("Closed connection");
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        log.info("Call get prepareStatement");
        if (connection == null) {
            connection = getConnection();
        }
        return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS,
                Statement.KEEP_CURRENT_RESULT);
    }


}
