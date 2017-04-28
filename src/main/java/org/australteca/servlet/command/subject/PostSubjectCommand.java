package org.australteca.servlet.command.subject;

import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 26/04/17.
 */
public class PostSubjectCommand implements Command {

    @Override
    public void execute(HttpServletContext context) throws IOException, ServletException {
        String subjectName = context.getServletRequest().getParameter(SUBJECT_NAME_PARAM);

        SubjectDao subjectDAO = new SubjectDao();

        Subject subject = subjectDAO.getByName(subjectName);

        context.getServletRequest().setAttribute(SUBJECT_COMMENTARY_LIST, subject.getCommentaryList());
        context.getServletRequest().setAttribute(SUBJECT_PROFESSOR_LIST, subject.getProfessors());
        context.getServletRequest().setAttribute(SUBJECT_NOTES_LIST, subject.getNoteList());

        context.getServletRequest().setAttribute(SUBJECT_NAME_PARAM, subjectName);
        context.getServletRequest().setAttribute(SUBJECT_SCORE, subject.getScore());
        context.forwardRequest("/mainMenu/subjectExample.jsp");
    }

    @Override
    public Command create() {
        return null;
    }
}
