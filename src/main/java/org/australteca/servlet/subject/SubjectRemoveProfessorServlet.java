package org.australteca.servlet.subject;

import org.australteca.dao.ProfessorDAO;
import org.australteca.dao.SubjectDAO;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;

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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        Integer professorID = Integer.parseInt(req.getParameter(PROFESSOR_ID_PARAM));

        SubjectDAO subjectDAO = new SubjectDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        Professor professor = professorDAO.getProfessor(professorID);
        Subject subject = subjectDAO.getByName(subjectName);

        professor.getSubjects().remove(subject);
        subject.getProfessors().remove(professor);

        professorDAO.merge(professor);
        subjectDAO.merge(subject);

        req.getRequestDispatcher("a donde sea").forward(req, resp);
    }
}
