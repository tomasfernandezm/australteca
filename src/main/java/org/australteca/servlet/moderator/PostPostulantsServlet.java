package org.australteca.servlet.moderator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.australteca.Constants;
import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.Professor;
import org.australteca.entity.SubjectModeratorRelationship;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

        String list = req.getParameter(Constants.LIST_TYPE_PARAM);

        SubjectModeratorRelationshipDao smrDao = new SubjectModeratorRelationshipDao();

        if(list == null) {
            List<SubjectModeratorRelationship> moderators = smrDao.getByAcceptance(true);
            List<SubjectModeratorRelationship> waitingAcceptance = smrDao.getByAcceptance(false);

            ProfessorDao professorDao = new ProfessorDao();
            List<Professor> professors = professorDao.list();

            req.setAttribute(Constants.PROFESSOR_LIST, professors);
            req.setAttribute(Constants.ACCEPTED_LIST, moderators);
            req.setAttribute(Constants.WAITING_LIST, waitingAcceptance);

            req.getRequestDispatcher("/mainMenu/moderators.jsp").forward(req, resp);

        }else if(list.equals(Constants.ACCEPTED_LIST)){

            List<SubjectModeratorRelationship> moderators = smrDao.getByAcceptance(true);
            req.setAttribute(Constants.ACCEPTED_LIST, moderators);
            List<SubjectUserPair> result = new ArrayList<>();
            for(SubjectModeratorRelationship smr: moderators){
                result.add(new SubjectUserPair(smr.getSubject().getSubjectName(), smr.getUser().getEmail()));
            }
            resp.setContentType("application/json");
            String responseJSON = new Gson().toJson(result);
            resp.getWriter().write(responseJSON);

         }else{

            List<SubjectModeratorRelationship> waitingAcceptance = smrDao.getByAcceptance(false);
            req.setAttribute(Constants.WAITING_LIST, waitingAcceptance);
            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(waitingAcceptance));
        }
    }

    class SubjectUserPair{

        private String subjectName;
        private String userEmail;

        public SubjectUserPair(String subjectName, String userEmail) {
            this.subjectName = subjectName;
            this.userEmail = userEmail;
        }
    }
}

