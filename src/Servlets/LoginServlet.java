package Servlets;

import DAO.DBConnector;
import DAO.Issues;
import DAO.Subscriptions;
import DAO.Users;
import Entities.Issue;
import Entities.Subscription;
import Entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(true);
        List<User> users = Users.retrieveUsers();

        for (User u: users) {
            if (u.getUsername().equals(request.getParameter("username"))) {
                if (u.getPassword().equals(request.getParameter("userpass"))) {
                    session.setAttribute("userName", request.getParameter("username"));
                    session.setAttribute("password", request.getParameter("userpass"));
                    if (u.isAdmin()) {
                        logger.info("Administrator logged in");
                        List<Issue> issues = Issues.retrieveIssues();
                        request.setAttribute("issues", issues);
                        users.removeIf(User::isAdmin);
                        request.setAttribute("users", users);
                        request.getRequestDispatcher("adminhome.jsp").forward(request, response);
                        return;
                    } else {
                        logger.info("User logged in");
                        List<Subscription> subscriptions = Subscriptions.retrieveSubscriptions();
                        subscriptions.removeIf(subscription -> !(subscription.getUser().getUsername().equals(session.getAttribute("userName"))));
                        request.setAttribute("subscriptions", subscriptions);
                        request.getRequestDispatcher("userhome.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body onload=\"alert('Incorrect user or password.')\"></body></html>");
    }
}
