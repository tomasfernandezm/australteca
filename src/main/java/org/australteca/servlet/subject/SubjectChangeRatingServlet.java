package org.australteca.servlet.subject;

import com.google.gson.Gson;
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

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 29/04/17.
 */
public class SubjectChangeRatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        Integer rating = Integer.parseInt(req.getParameter(Constants.SUBJECT_SCORE_PARAM));

        String email = req.getRemoteUser();
        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        Integer other = user.getSubjectScores().put(subjectName, rating);
        if(other == null) other = 0;

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);
        if(other != 0) {
            double oldTotalScore = subject.getScore() * subject.getAmountOfScores();
            double totalScore = oldTotalScore - other + rating;
            double score = totalScore / subject.getAmountOfScores();
            subject.setScore(score);
        }else{
            subject.addToScore(rating);
        }
        subjectDao.merge(subject);

        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
