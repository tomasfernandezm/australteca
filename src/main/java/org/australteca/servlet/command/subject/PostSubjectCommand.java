package org.australteca.servlet.command.subject;

import org.australteca.dao.SubjectDao;
import org.australteca.entity.Subject;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;
import static org.australteca.Constants.SUBJECT_NAME_PARAM;
import static org.australteca.Constants.SUBJECT_SCORE;

/**
 * Created by tomi on 26/04/17.
 */
public class PostSubjectCommand implements Command {

    @Override
    public void execute(HttpContext context) throws IOException, ServletException {
        String subjectName = context..getParameter(SUBJECT_NAME_PARAM);

        SubjectDao subjectDAO = new SubjectDao();

        Subject subject = subjectDAO.getByName(subjectName);

        req.setAttribute(SUBJECT_COMMENTARY_LIST, subject.getCommentaryList());
        req.setAttribute(SUBJECT_PROFESSOR_LIST, subject.getProfessors());
        req.setAttribute(SUBJECT_NOTES_LIST, subject.getNoteList());

        req.setAttribute(SUBJECT_NAME_PARAM, subjectName);
        req.setAttribute(SUBJECT_SCORE, subject.getScore());
        req.getRequestDispatcher("/mainMenu/subjectExample.jsp").forward(req, resp);
    }

    @Override
    public Command create() {
        return null;
    }
}
