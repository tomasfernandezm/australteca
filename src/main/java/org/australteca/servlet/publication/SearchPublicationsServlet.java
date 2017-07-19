package org.australteca.servlet.publication;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 19/07/17.
 */
public class SearchPublicationsServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numberOfPublicationsPerPage = 4;

        String title = req.getParameter(Constants.PUBLICATION_NAME);

        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publications = publicationDao.search(title);

        List<PublicationWrapper> investigations = new ArrayList<>();
        List<PublicationWrapper> works = new ArrayList<>();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        for(Publication p: publications){
            if(p.getRole().equals(Constants.WORK_PUBLICATION)){
                if(p.getAuthor().equals(user)) works.add(new PublicationWrapper(p, true));
                else works.add(new PublicationWrapper(p, false));
            }else if(p.getRole().equals(Constants.INVESTIGATION_PUBLICATION)){
                if(p.getAuthor().equals(user)) investigations.add(new PublicationWrapper(p, true));
                else investigations.add(new PublicationWrapper(p, false));
            }
        }

        req.setAttribute(Constants.WORK_PUBLICATION_LIST, works);
        req.setAttribute(Constants.INVESTIGATION_PUBLICATION_LIST, investigations);

        req.getRequestDispatcher("/jsp/work.jsp").forward(req, resp);

    }
}
