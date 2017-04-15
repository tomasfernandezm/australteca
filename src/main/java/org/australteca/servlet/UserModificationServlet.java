package org.australteca.servlet;

import org.australteca.Constants;
import org.australteca.dao.UserDAO;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 11/04/17.
 */
public class UserModificationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter(NAME_PARAM);
        String lname = req.getParameter(LAST_NAME_PARAM);
        String email = req.getParameter(EMAIL_PARAM);
        String password = req.getParameter(PASSWORD_PARAM);
        String passwordC = req.getParameter(PASSWORD_CONFIRMATION_PARAM);
        String career = req.getParameter(CAREER_PARAM);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail(req.getRemoteUser());

        user.setFirstName(name);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(password);
        user.setCourse(career);

        userDAO.merge(user);

        req.setAttribute(STATUS, 0);
        req.getRequestDispatcher("/mainMenu/userSettings.jsp").forward(req, resp);
    }
}
