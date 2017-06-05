package org.australteca.servlet.user;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 24/05/17.
 */
public class UserGetSubjectRatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getRemoteUser();
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        Integer rating = user.getSubjectScores().get(subjectName);
        if(rating == null) rating = 0;

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(rating));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
