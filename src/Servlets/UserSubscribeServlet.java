package Servlets;

import DAO.DBConnector;
import DAO.Issues;
import DAO.Subscriptions;
import Entities.Issue;
import Entities.Subscription;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "UserSubscribeServlet", urlPatterns = "/usersubscribe")
public class UserSubscribeServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UserSubscribeServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Issue> issues = Issues.retrieveIssues();
        List<Subscription> subscriptions = Subscriptions.retrieveSubscriptions();
        HttpSession session = request.getSession(true);
        Iterator<Issue> iter =  issues.iterator();
        while(iter.hasNext()) {
            Issue i = iter.next();
            for (Subscription s: subscriptions) {
                if ((s.getIssue().getId() == i.getId()) && (s.getUser().getUsername().equals(session.getAttribute("userName")))) {
                    iter.remove();
                }
            }
        }
        request.setAttribute("issues", issues);
        request.getRequestDispatcher("usersubscribe.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
