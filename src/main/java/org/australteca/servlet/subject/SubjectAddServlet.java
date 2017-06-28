package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 08/04/17.
 */

public class SubjectAddServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        SubjectDao subjectDAO = new SubjectDao();

        Integer status = subjectDAO.add(new Subject(subjectName));

        if(status != null) req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        else req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, false);
        req.getRequestDispatcher("/jsp/subjects.jsp").forward(req, resp);
    }
}
