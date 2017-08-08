package DAO;

import Entities.Issue;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Issues {

    private static final Logger logger = LogManager.getLogger(Issues.class);

    public static List<Issue> retrieveIssues() {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;
        ResultSet rs;
        List<Issue> issues = new ArrayList<>();

        try {
            statement = con.prepareStatement("periodical");
            rs = statement.executeQuery("SELECT * FROM periodical.issues");

            while (rs.next()) {
                Issue issue = new Issue();
                issue.setId(rs.getInt("id_issue"));
                issue.setName(rs.getString("name"));
                issue.setMonthlyCost(rs.getDouble("cost"));
                issues.add(issue);
            }

            con.close();
        } catch (SQLException e) {
            logger.error("Retrieving issues error");
        }
        return issues;
    }

    public static void addIssue (Issue issue) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;

        try {
            statement = con.prepareStatement("periodical");
            StringBuilder query = new StringBuilder("INSERT INTO periodical.issues (name, cost) VALUES ('");
            query.append(issue.getName());
            query.append("', '");
            query.append(issue.getMonthlyCost());
            query.append("');");
            statement.executeUpdate(query.toString());
            con.close();
        } catch (SQLException e) {
            logger.error("Adding issue error");
        }
    }

    public static void removeIssueById (Integer id) {
        DBConnector connector = new DBConnector();
        Connection con = connector.getConnection();

        PreparedStatement statement;

        try {
            statement = con.prepareStatement("periodical");
            statement.execute("DELETE FROM periodical.issues WHERE id_issue='" + id + "';");
            con.close();
        } catch (SQLException e) {
            logger.error("Removing issue error");
        }
    }
}