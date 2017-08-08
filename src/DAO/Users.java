package DAO;

import Entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {

    private static final Logger logger = LogManager.getLogger(Users.class);

    public static List<User> retrieveUsers() {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;
        ResultSet rs;
        List<User> users = new ArrayList<>();

        try {
            statement = con.prepareStatement("SELECT * FROM periodical.users");
            rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_user"));
                user.setAdmin(rs.getBoolean("is_admin"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                users.add(user);
            }
            con.close();
        } catch (SQLException e) {
            logger.error("Retriving users error");
        }
        return users;
    }

    public static User getUserById(Integer id) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;
        ResultSet rs;
        User user = new User();

        try {
            statement = con.prepareStatement("periodical");
            rs = statement.executeQuery("SELECT * FROM periodical.users WHERE id_user='" + id + "'");
            rs.next();
            user.setId(rs.getInt("id_user"));
            user.setAdmin(rs.getBoolean("is_admin"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            con.close();
        } catch (SQLException e) {
            logger.error("Retrieving user error");
        }
        return user;
    }

    public static User getUserByUsername(String username) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;
        ResultSet rs;
        User user = new User();

        try {
            statement = con.prepareStatement("periodical");
            rs = statement.executeQuery("SELECT * FROM periodical.users WHERE username='" + username + "'");
            rs.next();
            user.setId(rs.getInt("id_user"));
            user.setAdmin(rs.getBoolean("is_admin"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            con.close();
        } catch (SQLException e) {
            logger.error("Retrieving user error");
        }
        return user;
    }

    public static void addUser(User user) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;

        try {
            statement = con.prepareStatement("periodical");
            StringBuilder query = new StringBuilder("INSERT INTO periodical.users (username, password, is_admin, first_name, last_name) VALUES ('");
            query.append(user.getUsername());
            query.append("', '");
            query.append(user.getPassword());
            query.append("', '");
            query.append(user.isAdmin() ? 1 : 0);
            query.append("', '");
            query.append(user.getFirstName());
            query.append("', '");
            query.append(user.getLastName());
            query.append("');");
            statement.executeUpdate(query.toString());
            con.close();
        } catch (SQLException e) {
            logger.error("Adding user error");
        }
    }
}
