package Servlets;

import DAO.Issues;
import DAO.Users;
import Entities.Issue;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddIssueServlet", urlPatterns = "/addissue")
public class AddIssueServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Issue issue = new Issue();
        issue.setName(request.getParameter("issue"));
        issue.setCost(Double.parseDouble(request.getParameter("cost")));
        String period = request.getParameter("period");
        switch (period) {
            case "weekly": issue.setWeeksPeriod(1); break;
            case "twoWeeks": issue.setWeeksPeriod(2); break;
            case "monthly": issue.setWeeksPeriod(4); break;
        }
        Issues.addIssue(issue);
        List<Issue> issues = Issues.retrieveIssues();
        request.setAttribute("issues", issues);
        List<User> users = Users.retrieveUsers();
        users.removeIf(User::isAdmin);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/adminhome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
