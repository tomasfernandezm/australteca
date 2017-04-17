package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.SubjectDAO;
import org.australteca.dao.UserDAO;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import sun.security.ssl.SunJSSE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class UserAddSubjectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        String email = req.getRemoteUser();

        UserDAO userDAO = new UserDAO();
        SubjectDAO subjectDAO = new SubjectDAO();

        User user = userDAO.getUserByEmail(email);
        Subject subject = subjectDAO.getByName(subjectName);

        user.getSubjects().add(subject);
        subject.getSubscribedUsers().add(user);

        userDAO.merge(user);
        subjectDAO.merge(subject);

        req.getRequestDispatcher("a donde sea").forward(req, resp);
    }
}
