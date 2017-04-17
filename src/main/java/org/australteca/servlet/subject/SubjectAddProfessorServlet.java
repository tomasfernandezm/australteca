package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.ProfessorDAO;
import org.australteca.dao.SubjectDAO;
import org.australteca.dao.UserDAO;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class SubjectAddProfessorServlet extends HttpServlet {

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

        professor.getSubjects().add(subject);
        subject.getProfessors().add(professor);

        professorDAO.merge(professor);
        subjectDAO.merge(subject);

        req.getRequestDispatcher("a donde sea").forward(req, resp);
    }
}
