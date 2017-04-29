package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;

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

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        subject.addToScore(rating);
        subjectDao.merge(subject);

        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
