package org.australteca.servlet.user;

import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class UserRemoveSubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        String email = req.getRemoteUser();

        UserDao userDAO = new UserDao();
        SubjectDao subjectDAO = new SubjectDao();

        User user = userDAO.getUserByEmail(email);
        Subject subject = subjectDAO.getByName(subjectName);

        user.getSubjects().remove(subject);
        subject.getSubscribedUsers().remove(user);

        userDAO.merge(user);
        subjectDAO.merge(subject);

        req.getRequestDispatcher("a donde sea").forward(req, resp);
    }
}
