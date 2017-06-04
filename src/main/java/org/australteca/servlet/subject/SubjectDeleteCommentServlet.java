package org.australteca.servlet.subject;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.CommentaryDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.entity.Commentary;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.servlet.ModeratorChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tomi on 17/05/17.
 */
public class SubjectDeleteCommentServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter(Constants.COMMENTARY_ID);
        String subjectName = req.getParameter(Constants.SUBJECT_NAME_PARAM);
        Integer commentaryID;
        if(id != null) {
            commentaryID = Integer.parseInt(id);
            CommentaryDao commentaryDao = new CommentaryDao();
            Commentary commentary = commentaryDao.get(commentaryID);

            String commentaryAuthor = commentary.getAuthor().getEmail();

            if(req.getRemoteUser().equals(commentaryAuthor) || new ModeratorChecker().check(commentaryAuthor, subjectName)) {

                SubjectDao subjectDao = new SubjectDao();
                Subject subject = subjectDao.getByName(subjectName);

                subject.getCommentaryList().remove(commentary);

                resp.setContentType("application/json");
                resp.getWriter().write((new Gson()).toJson("OK"));
            }
        }
    }
}
