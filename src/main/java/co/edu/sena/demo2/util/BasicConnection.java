package co.edu.sena.demo2.util;


import java.sql.*;

public class BasicConnection {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/uu?serve rTimezone=America/Bogota";
        String username = "oscar";
        String password = "";
        String sql;
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            sql = "SELECT * FROM uu.usuario";
            try {
                conn =
                        DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                System.out.print(rs.getString("user_firstname"));
                System.out.print("");

                System.out.println(rs.getString("user_lastname"));
            }
        }  finally {
            try {
                assert rs != null;
                rs.close();
                stm.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}