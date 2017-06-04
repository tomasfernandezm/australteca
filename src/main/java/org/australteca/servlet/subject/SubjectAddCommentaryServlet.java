package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.CommentaryDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Commentary;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.utils.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 28/04/17.
 */
public class SubjectAddCommentaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        try {
            String email = req.getRemoteUser();
            UserDao userDao = new UserDao();
            User user = userDao.getUserByEmail(email);

            SubjectDao subjectDao = new SubjectDao();
            Subject subject = subjectDao.getByName(subjectName);

            String commentaryText = req.getParameter(Constants.COMMENTARY);
            Commentary commentary = new Commentary(commentaryText, user, subject);

            CommentaryDao commentaryDao = new CommentaryDao();
            commentaryDao.add(commentary);

            subject.getCommentaryList().add(commentary);
            user.getCommentaries().add(commentary);

            subjectDao.merge(subject);
            userDao.merge(user);
        }catch(IllegalStateException e){
            Transaction tx = HibernateUtil.getCurrentSession().getTransaction();
            if(tx.isActive()) tx.rollback();
        }
        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
