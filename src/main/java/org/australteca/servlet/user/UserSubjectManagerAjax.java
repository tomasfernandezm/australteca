package org.australteca.servlet.user;

import com.google.gson.Gson;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

import static org.australteca.Constants.FAVORITE_PARAM;
import static org.australteca.Constants.MAKE_FAVORITE;

/**
 * Created by tomasforman on 13/5/17.
 */
public class UserSubjectManagerAjax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String subjectName = req.getParameter("subject");
        Boolean status = Boolean.parseBoolean(req.getParameter("status"));

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        if(status){
            user.getSubjects().add(subject);
            subject.getUserList().add(user);
        }else{
            user.getSubjects().remove(subject);
            subject.getUserList().remove(user);
        }

        subjectDao.merge(subject);
        userDao.merge(user);

        resp.setContentType("application/json");
        //resp.getWriter().write();
    }
}
