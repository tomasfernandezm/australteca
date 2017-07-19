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
 * Created by tomi on 14/07/17.
 */
public class ListPublicationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int numberOfPublicationsPerPage = 4;

        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publications = publicationDao.list();

        List<PublicationWrapper> investigations = new ArrayList<>();
        List<PublicationWrapper> works = new ArrayList<>();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        int numberOfWorks = 0;
        int numberOfInvest = 0;

        for(Publication p: publications){
            if(p.getRole().equals(Constants.WORK_PUBLICATION) && numberOfWorks < 4){
                if(p.getAuthor().equals(user)) works.add(new PublicationWrapper(p, true));
                else works.add(new PublicationWrapper(p, false));
                numberOfWorks++;
            }else if(p.getRole().equals(Constants.INVESTIGATION_PUBLICATION) && numberOfInvest < 4){
                if(p.getAuthor().equals(user)) investigations.add(new PublicationWrapper(p, true));
                else investigations.add(new PublicationWrapper(p, false));
                numberOfInvest++;
            }
        }

        req.setAttribute(Constants.WORK_PUBLICATION_LIST, works);
        req.setAttribute(Constants.INVESTIGATION_PUBLICATION_LIST, investigations);

        req.setAttribute(Constants.WORK_TOTAL_PAGES, calculateNumberOfPages(works.size(), 4));
        req.setAttribute(Constants.INVESTIGATION_TOTAL_PAGES, calculateNumberOfPages(investigations.size(), 4));
        req.getRequestDispatcher("/jsp/work.jsp").forward(req, resp);

    }

    private int calculateNumberOfPages(int listSize, int pubsPerPage){
        if(listSize % pubsPerPage == 0) return listSize/pubsPerPage;
        else return listSize/pubsPerPage + 1;
    }

}
