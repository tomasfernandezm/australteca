package org.australteca.servlet.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 16/04/17.
 */
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();
        UserDao userDAO = new UserDao();

        User user = userDAO.getUserByEmail(email);
        Integer userID = user.getId();

        userDAO.delete(userID);

        req.getRequestDispatcher("/logout.jsp").forward(req, resp);
    }
}
