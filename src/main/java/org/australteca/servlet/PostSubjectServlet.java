package org.australteca.servlet;

import org.australteca.dao.SubjectDAO;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 13/04/17.
 */
public class PostSubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);

        SubjectDAO subjectDAO = new SubjectDAO();

        Subject subject = subjectDAO.getByName(subjectName);

        req.setAttribute(SUBJECT_COMMENTARY_LIST, subject.getCommentaryList());
        req.setAttribute(SUBJECT_PROFESSOR_LIST, subject.getProfessors());
        // req.setAttribute(SUBJECT_FILES_LIST, subject.getFiles());

        req.getRequestDispatcher("/mainMenu/subjectExample.jsp").forward(req, resp);

    }
}
