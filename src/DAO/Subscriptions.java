package DAO;

import Entities.Issue;
import Entities.Subscription;
import Entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Subscriptions {

    private static final Logger logger = LogManager.getLogger(Subscriptions.class);

    public static List<Subscription> retrieveSubscriptions () {

        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;
        PreparedStatement secondaryStatement;
        ResultSet rs;
        ResultSet sRs;
        List<Subscription> subscriptions = new ArrayList<>();

        try {
            secondaryStatement = con.prepareStatement("periodical");
            statement = con.prepareStatement("periodical");
            rs = statement.executeQuery("SELECT * FROM periodical.payments");

            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setId(rs.getInt("id_payment"));
                subscription.setSubscriptionDate(rs.getDate("payment_date"));
                subscription.setExpirationDate(rs.getDate("expiration_date"));

                User user = new User();
                Integer userId = rs.getInt("user_id");
                sRs = secondaryStatement.executeQuery("SELECT * FROM periodical.users WHERE id_user= \'" + userId +"\'");
                sRs.next();
                user.setId(sRs.getInt("id_user"));
                user.setAdmin(sRs.getBoolean("is_admin"));
                user.setUsername(sRs.getString("username"));
                user.setPassword(sRs.getString("password"));
                user.setFirstName(sRs.getString("first_name"));
                user.setLastName(sRs.getString("last_name"));
                subscription.setUser(user);

                Issue issue = new Issue();
                Integer issueId = rs.getInt("issue_id");
                sRs = secondaryStatement.executeQuery("SELECT * FROM periodical.issues WHERE id_issue= \'" + issueId +"\'");
                if(sRs.next()) {
                    issue.setId(sRs.getInt("id_issue"));
                    issue.setName(sRs.getString("name"));
                    issue.setCost(sRs.getDouble("cost"));
                    issue.setWeeksPeriod(sRs.getInt("period"));
                    subscription.setIssue(issue);
                }
                subscriptions.add(subscription);

            }
            con.close();
        } catch (SQLException e) {
            logger.error("Retrieving subscriptions error");
        }
        return subscriptions;
    }

    public static void updateSubscriptions(List<Subscription> newSubscriptions) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;

        try {
            statement = con.prepareStatement("periodical");
            for (Subscription s: newSubscriptions) {
                StringBuilder query = new StringBuilder("INSERT INTO periodical.payments (user_id, payment_date, issue_id, expiration_date) VALUES ('");
                query.append(s.getUser().getId());
                query.append("', '");
                query.append(new java.sql.Date(s.getSubscriptionDate().getTime()));
                query.append("', '");
                query.append(s.getIssue().getId());
                query.append("', '");
                query.append(new java.sql.Date(s.getExpirationDate().getTime()));
                query.append("');");
                statement.executeUpdate(query.toString());
            }
            con.close();
        } catch (SQLException e) {
            logger.error("Updating subscriptions error");
        }
    }

    public static void removeSubscriptionById(Integer id) {
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("periodical");
            statement.execute("DELETE FROM periodical.payments WHERE id_payment='" + id +"';");
            connection.close();
        } catch (SQLException e) {
            logger.error("Removing subscription error");
        }
    }

    public static void removeSubscriptionByIssueId(Integer id) {
        DBConnector connector = new DBConnector();
        Connection connection = connector.getConnection();

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("periodical");
            statement.execute("DELETE FROM periodical.payments WHERE issue_id='" + id +"';");
            connection.close();
        } catch (SQLException e) {
            logger.error("Removing subscription error");
        }
    }
}
