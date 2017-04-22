package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.FAVORITE_PARAM;
import static org.australteca.Constants.MAKE_FAVORITE;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 22/04/17.
 */
public class UserSubjectManagerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = req.getParameter(FAVORITE_PARAM);
        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        if(status.equals(MAKE_FAVORITE)){
            user.getSubjects().add(subject);
            subject.getUserList().add(user);
        }else{
            user.getSubjects().remove(subject);
            subject.getUserList().remove(user);
        }

        subjectDao.merge(subject);
        userDao.merge(user);

        resp.sendRedirect("/listSubjects");
    }
}
