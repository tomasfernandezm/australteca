package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.NoteDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.Note;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.servlet.ModeratorChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

        String noteStringID = req.getParameter(Constants.NOTE_ID_PARAM);
        Integer id = null;
        if(noteStringID != null) id = Integer.parseInt(noteStringID);
        String subjectName = req.getParameter(SUBJECT_NAME_PARAM);

        if(id != null) {

            NoteDao noteDao = new NoteDao();
            Note note = noteDao.get(id);
            if(req.getRemoteUser().equals(note.getAuthor().getEmail()) || new ModeratorChecker().check(note.getAuthor().getEmail(), subjectName)) {

                SubjectDao subjectDao = new SubjectDao();
                Subject subject = subjectDao.getByName(subjectName);

                subject.getNoteList().remove(note);
            }
        }
        resp.sendRedirect("/postSubject?"+ SUBJECT_NAME_PARAM + "="+subjectName);
    }
}
