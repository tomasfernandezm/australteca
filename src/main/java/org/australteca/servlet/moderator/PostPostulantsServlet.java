package org.australteca.servlet.moderator;

import org.australteca.Constants;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.SubjectModeratorRelationship;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tomi on 10/05/17.
 */
public class PostPostulantsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SubjectModeratorRelationshipDao smrDao = new SubjectModeratorRelationshipDao();
        List<SubjectModeratorRelationship> moderators = smrDao.getByAcceptance(true);
        List<SubjectModeratorRelationship> waitingAcceptance = smrDao.getByAcceptance(false);

        req.setAttribute(Constants.ACCEPTED_LIST, moderators);
        req.setAttribute(Constants.WAITING_LIST, waitingAcceptance);

        req.getRequestDispatcher("/mainMenu/moderators.jsp").forward(req, resp);
    }
}
