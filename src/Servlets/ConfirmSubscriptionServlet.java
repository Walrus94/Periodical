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
import java.util.Calendar;
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
                Calendar c = Calendar.getInstance();
                if (iss.getId().equals(Integer.parseInt(checked[i]))) {
                    sub.setIssue(iss);
                    c.setTime(new Date());
                    String subscriptionLength = request.getParameter("subscriptionLength" + iss.getId());
                    switch (subscriptionLength) {
                        case "oneMonth": c.add(Calendar.MONTH, 1); break;
                        case "threeMonths": c.add(Calendar.MONTH, 3); break;
                        case "sixMonths": c.add(Calendar.MONTH, 6); break;
                        case "year": c.add(Calendar.YEAR, 1); break;
                    }
                    sub.setSubscriptionDate(new Date());
                    sub.setExpirationDate(c.getTime());
                }
            }
            subscriptions.add(sub);
        }
        Subscriptions.updateSubscriptions(subscriptions);
        subscriptions = Subscriptions.retrieveSubscriptions();
        subscriptions.removeIf(subscription -> !(subscription.getUser().getUsername().equals(session.getAttribute("userName"))));
        Double monthlyPayment = 0.0;
        for (Subscription s: subscriptions) {
            monthlyPayment += s.getIssue().getCost() * (4 / s.getIssue().getWeeksPeriod());
        }
        request.setAttribute("monthlyPayment", monthlyPayment);
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }
}
