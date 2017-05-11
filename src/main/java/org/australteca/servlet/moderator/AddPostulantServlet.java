package org.australteca.servlet.moderator;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 10/05/17.
 */
public class AddPostulantServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userMail = req.getParameter(Constants.USER_EMAIL_PARAM);
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(userMail);

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        SubjectModeratorRelationship subjectModeratorRelationship = new SubjectModeratorRelationship(subject, user);
        SubjectModeratorRelationshipDao sbmrDAO = new SubjectModeratorRelationshipDao();
        sbmrDAO.add(subjectModeratorRelationship);

        resp.sendRedirect("/mainMenu/moderators.jsp");
    }
}
