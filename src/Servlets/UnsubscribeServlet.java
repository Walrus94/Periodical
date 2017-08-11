package Servlets;

import DAO.Subscriptions;
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
import java.util.List;

@WebServlet(name = "UnsubscribeServlet", urlPatterns = "/unsubscribe")
public class UnsubscribeServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(UnsubscribeServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriptions.removeSubscriptionById(Integer.parseInt(request.getParameter("subscriptionid")));
        List<Subscription> subscriptions = Subscriptions.retrieveSubscriptions();
        HttpSession session = request.getSession();
        subscriptions.removeIf(subscription -> !(subscription.getUser().getUsername().equals(session.getAttribute("userName"))));
        Double monthlyPayment = 0.0;
        for (Subscription s: subscriptions) {
            monthlyPayment += s.getIssue().getCost() * (4 / s.getIssue().getWeeksPeriod());
        }
        request.setAttribute("monthlyPayment", monthlyPayment);
        request.setAttribute("subscriptions", subscriptions);
        logger.info("User canceled subscription");
        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
