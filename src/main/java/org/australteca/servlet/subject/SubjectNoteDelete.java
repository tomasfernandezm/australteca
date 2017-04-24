package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.NoteDao;
import org.australteca.dao.SubjectDao;
import org.australteca.entity.Note;
import org.australteca.entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.SUBJECT_NAME_PARAM;

/**
 * Created by tomi on 24/04/17.
 */
public class SubjectNoteDelete extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter(Constants.NOTE_ID_PARAM));
        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);

        NoteDao noteDao = new NoteDao();
        Note note = noteDao.get(id);

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        subject.getNoteList().remove(note);

        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
