package org.australteca.servlet.publication;

import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.User;
import org.dom4j.util.UserDataAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 27/05/17.
 */
public class RemovePublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();
        Integer id = Integer.parseInt(req.getParameter(Constants.PUBLICATION_ID));

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);

        PublicationDao publicationDao = new PublicationDao();
        Publication publication = publicationDao.get(id);

        if(publication.getAuthor().getEmail().equals(email)){
            publicationDao.delete(id);
        }

        resp.setContentType("application/json");
        resp.getWriter().write("OK");

    }
}
