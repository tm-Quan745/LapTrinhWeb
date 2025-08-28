package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connect.DBConnect;
import model.User;

public class UserDaoImpl implements UserDao {
    private DBConnect db = new DBConnect();

    @Override
    public User get(String username) {
        User user = null;
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFullname(rs.getString("fullname"));
                    user.setAvatar(rs.getString("avatar"));
                    user.setRoleId(rs.getInt("roleid"));
                    user.setPhone(rs.getString("phone"));
                    user.setCreatedDate(rs.getTimestamp("createddate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public boolean insert(User user) {
        String sql = "INSERT INTO Users (username, password, email, fullname, roleid, createdate) "
                   + "VALUES (?, ?, ?, ?, ?, GETDATE())";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullname());   // có thể null
            ps.setInt(5, 2);   // mặc định roleid = 2 (user thường)

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
