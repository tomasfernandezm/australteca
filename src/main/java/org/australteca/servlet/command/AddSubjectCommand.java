package org.australteca.servlet.command;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */
public class AddSubjectCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        SubjectDao subjectDAO = new SubjectDao();

        Integer status = subjectDAO.add(new Subject(subjectName));

        if(status != null) req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        else req.setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, false);
        req.getRequestDispatcher("/mainMenu/subjects.jsp").forward(req, resp);
    }

    @Override
    public Command create() {
        return new AddSubjectCommand();
    }
}
