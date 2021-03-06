package Servlets;

import DAO.DBConnector;
import DAO.Users;
import Entities.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = Users.retrieveUsers();
        for (User u: users) {
            if (u.getUsername().equals(request.getParameter("username"))) {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<html><body onload=\"alert('User alredy exist')\"></body></html>");
                request.getRequestDispatcher("/registration.jsp").include(request, response);
                return;
            }
        }
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setAdmin(false);
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        Users.addUser(user);
        logger.info("New user registred");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body onload=\"alert('User succesfully registred.')\"></body></html>");
        request.getRequestDispatcher("/index.jsp").include(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
