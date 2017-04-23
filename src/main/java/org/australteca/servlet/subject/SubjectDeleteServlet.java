package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 16/04/17.
 */
public class SubjectDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);
        SubjectDao subjectDAO = new SubjectDao();

        Subject subject = subjectDAO.getByName(subjectName);
        Integer subjectID = subject.getId();

        subjectDAO.delete(subjectID);

        req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        resp.sendRedirect("/listSubjects"); //arreglar
    }
}
