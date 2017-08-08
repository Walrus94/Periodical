package Servlets;

import DAO.Subscriptions;
import Entities.Subscription;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Subscriptions.removeSubscriptionById(Integer.parseInt(request.getParameter("subscriptionid")));
        List<Subscription> subscriptions = Subscriptions.retrieveSubscriptions();
        HttpSession session = request.getSession();
        subscriptions.removeIf(subscription -> !(subscription.getUser().getUsername().equals(session.getAttribute("userName"))));
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("userhome.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
