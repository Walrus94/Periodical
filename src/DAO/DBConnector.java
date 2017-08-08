package DAO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static final Logger logger = LogManager.getLogger(DBConnector.class);

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            logger.error("Driver not registred");
        }
    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?useSSL=false", "root", "root");
            return connection;
        } catch (SQLException e) {
            logger.error("Connection not get");
            return null;
        }
    }
}