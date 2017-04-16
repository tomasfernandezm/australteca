package org.australteca.servlet.user;

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
 * Created by tomi on 16/04/17.
 */
public class UserPostServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();
        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUserByEmail(email);

        req.setAttribute(NAME_PARAM, user.getFirstName());
        req.setAttribute(LAST_NAME_PARAM, user.getLastName());
        req.setAttribute(EMAIL_PARAM, user.getEmail());
        req.setAttribute(CAREER_PARAM, user.getCourse());
        req.setAttribute(ROLE_PARAM, user.getRole());

        req.getRequestDispatcher("/mainMenu/userSettings.jsp").forward(req, resp);
    }
}
