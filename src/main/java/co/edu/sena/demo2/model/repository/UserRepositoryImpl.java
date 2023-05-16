package co.edu.sena.demo2.model.repository;

import co.edu.sena.demo2.User;
import co.edu.sena.demo2.util.ConnectionPool;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserRepositoryImpl implements Repository<User>{
private String sql = null;
    @Override
    public List<User>listAllObj() throws SQLException{
        sql ="select u.user_id, u.user_firstname, u.user_lasname, u.user_email" +
                "from usuario u order by u.user_lasname, u.user_firstame";
        List<User> users = new ArrayList<>();
        try (Connection conn= ConnectionPool.getConnection();
             Statement stmt =conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                User u = createObj(rs);
                users.add(u);

            }
            }
return users;
        }



    @Override
    public User byIdObj(Integer id) throws SQLException{
        sql ="select u.user_id, u.user_firstname, u.user_lasname, u.user_email" +
                "from usuario u where u.user_id = ?";
        User user = null;


        try (Connection conn= ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = createObj(rs);
                }

            }

        }

        return user;
    }
    @Override
    public Integer saveObj(User user) throws SQLException{
        int rowsAffected = 0;
        if (user.getUser_id() != null && user.getUser_id() > 0) {
            sql = "update usuario set user_firstname = ?, user_lastname = ?," + "user_password = aes_ebcrypt(?, '$2a$12$Og850tQgP/EWaV2Rqxd94.4Znf8IYo78cldeUDbEfpafylUxsFGMm')" + "where user_id = ?";
        }else{
            sql = "insert into usuario (user_firstname, user_lastname, user_email, user_password" + "values(upper(?), upper(?), lawer(?), " + "des_encrypt (?, '$2a$12$Og850tQgP/EWaV2Rqxd94.4Znf8IYo78cldeUDbEfpafylUxsFGMm'";

        }
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setString(1, user.getUser_firstname());
            ps.setString(2, user.getUser_lastname());
            ps.setString(3, user.getUser_email());
            ps.setString(4, user.getUser_password());
            if (user.getUser_id() != null && user.getUser_id() > 0) {
                ps.setInt(5, user.getUser_id());
            }
            rowsAffected = ps.executeUpdate();
        }
        return rowsAffected;
    }




    @Override
    public void deleteObj(Integer id) throws SQLException{
        sql = "delete from usuario where user_id = ?";
        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }

    }
@Override
    public User createObj(ResultSet rs) throws SQLException{
        User user = new User();
        user.setUser_id(rs.getInt("user_id"));
        user.setUser_firstname(rs.getString("user_firstname"));
        user.setUser_lastname(rs.getString("user_lastname"));
        user.setUser_email(rs.getString("user_email"));
        user.setUser_password(rs.getString("user_password"));
        return user;
 }
}



