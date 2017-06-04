package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.USER_COMMENTARY_LIST;
import static org.australteca.Constants.USER_SUBJECT_LIST;

/**
 * Created by tomi on 16/04/17.
 */
public class UserListPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();

        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByEmail(email);

        req.setAttribute(USER_COMMENTARY_LIST, user.getCommentaries());
        req.setAttribute(USER_SUBJECT_LIST, user.getSubjects());
        req.setAttribute(Constants.USER_FAVORITE_PUBLICATIONS_LIST, user.getFavoritePublications());
        req.setAttribute(Constants.USER_PUBLICATION_LIST, user.getPublication());

        req.getRequestDispatcher("/mainMenu/home.jsp").forward(req, resp);
    }
}
