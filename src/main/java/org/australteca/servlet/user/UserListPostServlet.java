package org.australteca.servlet.user;

import com.github.rjeschke.txtmark.Processor;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.australteca.Constants.*;
import static com.github.rjeschke.txtmark.Processor.*;

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
            publicationWrappers.add(new PublicationWrapper(p, Processor.process(p.getDescription())));
        }
        req.setAttribute(Constants.USER_PUBLICATION_LIST, publicationWrappers);

        req.setAttribute(AMOUNT_OF_UPLOADED_NOTES, user.getAmountOfNotes());
        req.setAttribute(AMOUNT_OF_COMMENTARIES, user.getCommentaries().size());

        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    public class PublicationWrapper{

        public Publication publication;
        public String htmlDescription;

        public PublicationWrapper(Publication publication, String htmlDescription) {
            this.publication = publication;
            this.htmlDescription = htmlDescription;
        }

        public Publication getPublication() {
            return publication;
        }

        public void setPublication(Publication publication) {
            this.publication = publication;
        }

        public String getHtmlDescription() {
            return htmlDescription;
        }

        public void setHtmlDescription(String htmlDescription) {
            this.htmlDescription = htmlDescription;
        }
    }
}
