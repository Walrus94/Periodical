package Servlets;

import DAO.DBConnector;
import DAO.Issues;
import DAO.Subscriptions;
import DAO.Users;
import Entities.Issue;
import Entities.Subscription;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ConfirmSubscriptionServlet", urlPatterns = "/usersubscribeconfirm")
public class ConfirmSubscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] checked = request.getParameterValues("selectedItems");
        HttpSession session = request.getSession(true);
        List<Issue> issues = Issues.retrieveIssues();
        List<Subscription> subscriptions = new ArrayList<>();
        for (int i = 0; i < checked.length; i++) {
            Subscription sub = new Subscription();
            sub.setUser(Users.getUserByUsername(session.getAttribute("userName").toString()));
            for (Issue iss: issues) {
                if (iss.getId().equals(Integer.parseInt(checked[i]))) {
                    sub.setIssue(iss);
                }
                sub.setSubscriptionDate(new Date());
            }
            subscriptions.add(sub);
        }
        Subscriptions.updateSubscriptions(subscriptions);
        subscriptions = Subscriptions.retrieveSubscriptions();
        subscriptions.removeIf(subscription -> !(subscription.getUser().getUsername().equals(session.getAttribute("userName"))));
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }
}
