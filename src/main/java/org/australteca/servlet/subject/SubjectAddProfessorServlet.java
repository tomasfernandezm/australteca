package org.australteca.servlet.subject;

import com.google.gson.Gson;
import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.australteca.Constants.*;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class SubjectAddProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        Integer professorID = Integer.parseInt(req.getParameter(PROFESSOR_ID_PARAM));

        SubjectDao subjectDAO = new SubjectDao();
        ProfessorDao professorDAO = new ProfessorDao();

        Professor professor = professorDAO.get(professorID);
        Subject subject = subjectDAO.getByName(subjectName);

        professor.getSubjects().add(subject);
        subject.getProfessors().add(professor);

        professorDAO.merge(professor);
        subjectDAO.merge(subject);

        List<String> response = new ArrayList<>();
        response.add(professor.getFirstName() +" "+ professor.getLastName());
        response.add(professor.getEmail());
        response.add(professor.getInformation());

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(response));
    }
}
