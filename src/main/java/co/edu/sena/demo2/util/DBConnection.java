package co.edu.sena.demo2.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "mysql -h aws.connect.psdb.cloud -u 5l2d4ybc9dmaski0mnbm -ppscale_pw_QlF50l0VMAQjJOOs51nneq9EZ0DvNveG5J3V4TDPXRd --ssl-mode=VERIFY_IDENTITY --ssl-ca=/etc/ssl/certs/ca-certificates.crt";
    private static final String USER = "5l2d4ybc9dmaski0mnbm";
    private static final String PASS = "pscale_pw_QlF50l0VMAQjJOOs51nneq9EZ0DvNveG5J3V4TDPXRd";
    private static BasicDataSource pool;
    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(URL);
            pool.setUsername(USER);
            pool.setPassword(PASS);

            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }

        return pool;
    }
    public static Connection getConnection() throws SQLException{
        return getInstance().getConnection();
    }
}