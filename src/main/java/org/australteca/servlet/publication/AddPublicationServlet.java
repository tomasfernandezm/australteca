package org.australteca.servlet.publication;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 27/05/17.
 */
public class AddPublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getRemoteUser();
        String pubName = req.getParameter(Constants.PUBLICATION_NAME);
        String pubDescription = req.getParameter(Constants.PUBLICATION_DESCRIPTION);
        String pubRequirements = req.getParameter(Constants.PUBLICATION_REQUIREMENTS);
        String pubTasks = req.getParameter(Constants.PUBLICATION_TASKS);
        String pubRole = req.getParameter(Constants.PUBLICATION_ROLE);

        UserDao userDao = new UserDao();
        User author = userDao.getUserByEmail(email);

        if (pubRole != null && (pubRole.equals(Constants.WORK_PUBLICATION) || pubRole.equals(Constants.INVESTIGATION_PUBLICATION))) {

            Publication publication = new Publication(pubName, author, pubDescription, pubRequirements, pubTasks, pubRole);
            PublicationDao publicationDao = new PublicationDao();
            Integer id = publicationDao.add(publication);
            Publication pub = publicationDao.get(id);
            author.getPublications().add(pub);
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson("OK"));
    }
}
