package org.australteca.servlet.subject;

import com.google.gson.Gson;
import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.australteca.servlet.ModeratorChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.PROFESSOR_ID_PARAM;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class SubjectRemoveProfessorServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        String toParse = req.getParameter(PROFESSOR_ID_PARAM);
        Integer professorID = null;
        if(toParse != null) professorID = Integer.parseInt(toParse);

        SubjectDao subjectDAO = new SubjectDao();
        ProfessorDao professorDAO = new ProfessorDao();


        if(professorID != null && new ModeratorChecker().check(req.getRemoteUser(), subjectName)) {

            Professor professor = professorDAO.get(professorID);
            Subject subject = subjectDAO.getByName(subjectName);

            professor.getSubjects().remove(subject);
            subject.getProfessors().remove(professor);

            professorDAO.merge(professor);
            subjectDAO.merge(subject);
            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson(professor.getEmail()));
        }
    }
}
