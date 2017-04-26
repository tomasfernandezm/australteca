package org.australteca.servlet.command.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by tomi on 12/04/17.
 */
public class AddSubjectCommand implements Command {

    @Override
    public void execute(HttpContext context) throws IOException, ServletException {
        String subjectName = context.getServletRequest().getParameter(Constants.SUBJECT_NAME_PARAM);
        SubjectDao subjectDAO = new SubjectDao();

        Integer status = subjectDAO.add(new Subject(subjectName));

        if(status != null) context.getServletRequest().setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        else context.getServletRequest().setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, false);
        context.forwardRequest("/mainMenu/subjects.jsp");
    }

    @Override
    public Command create() {
        return new AddSubjectCommand();
    }
}
