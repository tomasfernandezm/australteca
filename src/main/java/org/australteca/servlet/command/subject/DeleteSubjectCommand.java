package org.australteca.servlet.command.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.factory.CommandFactory;
import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 26/04/17.
 */
public class DeleteSubjectCommand implements Command{

    @Override
    public void execute(HttpContext context) throws IOException, ServletException {
        String subjectName = context.getServletRequest().getParameter(SUBJECT_NAME_PARAM);
        SubjectDao subjectDAO = new SubjectDao();

        Subject subject = subjectDAO.getByName(subjectName);
        Integer subjectID = subject.getId();

        subjectDAO.delete(subjectID);

        context.getServletRequest().setAttribute(Constants.OPERATION_SUCCESFUL_PARAM, true);
        context.redirect("/listSubjects");
    }

    @Override
    public Command create() {
        return new DeleteSubjectCommand();
    }
}
