package org.australteca.servlet;

import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 27/06/17.
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("loggedIn", true);
        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());
        String name = user.getFirstName() + " " +  user.getLastName();
        req.getSession().setAttribute(Constants.NAME_PARAM, name);
        resp.sendRedirect("/servlet/userListPost");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
