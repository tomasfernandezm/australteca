package org.australteca.servlet.subject;

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

import static org.australteca.Constants.*;

/**
 * Created by tomi on 13/04/17.
 */
public class SubjectPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        String email = req.getRemoteUser();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        SubjectDao subjectDAO = new SubjectDao();
        Subject subject = subjectDAO.getByName(subjectName);

        Integer userScore = user.getSubjectScores().getOrDefault(SUBJECT_USER_SCORE, 0);
        req.setAttribute(SUBJECT_USER_SCORE, userScore);

        req.setAttribute(SUBJECT_COMMENTARY_LIST, subject.getCommentaryList());
        req.setAttribute(SUBJECT_PROFESSOR_LIST, subject.getProfessors());
        req.setAttribute(SUBJECT_NOTES_LIST, subject.getNoteList());

        req.setAttribute(SUBJECT_NAME_PARAM, subjectName);
        req.setAttribute(SUBJECT_SCORE, new DecimalFormat("#.##").format(subject.getScore()));
        req.getRequestDispatcher("/mainMenu/subjectExample.jsp").forward(req, resp);

    }
}
