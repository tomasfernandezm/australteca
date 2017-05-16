package org.australteca.servlet.moderator;

import org.australteca.Constants;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.SubjectModeratorRelationship;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 10/05/17.
 */
public class AcceptPostulationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter(Constants.USER_EMAIL_PARAM);
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);

        SubjectModeratorRelationshipDao smrDao = new SubjectModeratorRelationshipDao();
        SubjectModeratorRelationship smr = smrDao.getByUserEmailAndSubjectName(email, subjectName);

        smr.accept();

        smrDao.merge(smr);

        resp.setContentType("application/json");

    }
}
