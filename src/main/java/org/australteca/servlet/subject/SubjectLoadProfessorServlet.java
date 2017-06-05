package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.ProfessorDao;
import org.australteca.entity.Professor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomasforman on 16/5/17.
 */
public class SubjectLoadProfessorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        String name = req.getParameter(Constants.PROFESSOR_NAME_PARAM);
        String lastName = req.getParameter(Constants.PROFESSOR_LAST_NAME_PARAM);
        String email = req.getParameter(Constants.PROFESSOR_EMAIL_PARAM);
        String information = req.getParameter(Constants.PROFESSOR_INFORMATION_PARAM);

        ProfessorDao professorDao = new ProfessorDao();

        Integer status = professorDao.add(new Professor(name,lastName,email,information));

        if(status != null) req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        else req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, false);

        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
