package Servlets;

import DAO.Subscriptions;
import DAO.Users;
import Entities.Subscription;
import Entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserInfoServlet", urlPatterns = "/userinfo")
public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = Users.getUserById(Integer.parseInt(request.getParameter("userid")));
        List<Subscription> subscriptions = Subscriptions.retrieveSubscriptions();
        subscriptions.removeIf(subscription -> subscription.getUser().getId() != user.getId());
        request.setAttribute("user", user);
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("userinfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
