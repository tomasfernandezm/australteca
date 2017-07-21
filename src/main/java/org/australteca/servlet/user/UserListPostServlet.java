package org.australteca.servlet.user;

import com.github.rjeschke.txtmark.Processor;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.entity.User;
import org.australteca.servlet.publication.PublicationWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 16/04/17.
 */
public class UserListPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("loggedIn") == null){
            req.getSession().setAttribute("loggedIn", true);
        }

        String email = req.getRemoteUser();

        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByEmail(email);

        req.setAttribute(USER_COMMENTARY_LIST, user.getCommentaries());
        req.setAttribute(USER_SUBJECT_LIST, user.getSubjects());

        List<PublicationWrapper> publicationWrappers = new ArrayList<>();
        for(Publication p: user.getPublications()){
            publicationWrappers.add(new PublicationWrapper(p, true));
        }
        req.setAttribute(Constants.USER_PUBLICATION_LIST, publicationWrappers);

        int amounOfModeratedSubjects = 0;
        for(SubjectModeratorRelationship smr: user.getSubjectModeratorRelationships()){
            if(smr.isAccepted()) amounOfModeratedSubjects++;
        }

        req.setAttribute(Constants.AMOUNT_OF_MODERATED_SUBJECTS, amounOfModeratedSubjects);
        req.setAttribute(AMOUNT_OF_UPLOADED_NOTES, user.getAmountOfNotes());
        req.setAttribute(AMOUNT_OF_COMMENTARIES, user.getCommentaries().size());

        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }
}
