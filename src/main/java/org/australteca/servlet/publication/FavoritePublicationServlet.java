package org.australteca.servlet.publication;

import org.australteca.dao.PublicationDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 03/06/17.
 */
public class FavoritePublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status = req.getParameter(FAVORITE_PARAM);
        String publicationIDString = req.getParameter(PUBLICATION_ID);
        Integer publicationID = Integer.parseInt(publicationIDString);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        PublicationDao publicationDao = new PublicationDao();
        Publication publication = publicationDao.get(publicationID);


        if(status.equals(MAKE_FAVORITE)){
            user.getFavoritePublications().add(publication);
            publication.getSuscribedUsers().add(user);
        }else{
            user.getFavoritePublications().remove(publication);
            publication.getSuscribedUsers().remove(user);
        }

        userDao.merge(user);

        resp.setContentType("application/json");
        //resp.getWriter().write();
    }
}
