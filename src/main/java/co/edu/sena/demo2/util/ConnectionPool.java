package co.edu.sena.demo2.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static String url = "jdbc:mysql://localhost:3306/uu?serverTimezone=America/Bogota";
    private static String user = "oscar";
    private static String pass = "";
    private static org.apache.commons.dbcp2.BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new org.apache.commons.dbcp2.BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(pass);
            pool.setInitialSize(5);
            pool.setMinIdle(5);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
        }
        return pool;
    }

    public static Connection getConnection()
            throws SQLException {
        return getInstance().getConnection();
    }
} // ConnectionPool}