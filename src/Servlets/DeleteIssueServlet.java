package Servlets;

import DAO.Issues;
import DAO.Subscriptions;
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

@WebServlet(name = "DeleteIssueServlet", urlPatterns = "/deleteissue")
public class DeleteIssueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Issues.removeIssueById(Integer.parseInt(request.getParameter("issueid")));
        Subscriptions.removeSubscriptionByIssueId(Integer.parseInt(request.getParameter("issueid")));
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
