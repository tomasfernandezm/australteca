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
import java.util.ConcurrentModificationException;
import java.util.List;

import com.github.rjeschke.txtmark.Processor;

/**
 * Created by tomi on 27/05/17.
 */
public class ListPublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publications = publicationDao.list();

        List<PublicationWrapper> investigations = new ArrayList<>();
        List<PublicationWrapper> works = new ArrayList<>();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        for(Publication p: publications){
            if(p.getRole().equals(Constants.WORK_PUBLICATION) && user.getPublications().contains(p)) works.add(new PublicationWrapper(p, true));
            else if(p.getRole().equals(Constants.WORK_PUBLICATION) && !user.getPublications().contains(p)) works.add(new PublicationWrapper(p, false));
            else if(p.getRole().equals(Constants.INVESTIGATION_PUBLICATION) && !user.getPublications().contains(p)) investigations.add(new PublicationWrapper(p, false));
            else{
                investigations.add(new PublicationWrapper(p, true));
            }
        }

        req.setAttribute(Constants.WORK_PUBLICATION_LIST, works);
        req.setAttribute(Constants.INVESTIGATION_PUBLICATION_LIST, investigations);

        req.getRequestDispatcher("/jsp/work.jsp").forward(req, resp);
    }

    public class PublicationWrapper{

        Publication publication;
        boolean favorite;
        String htmlDescription;

        PublicationWrapper(Publication publication, boolean favorite) {
            this.publication = publication;
            this.favorite = favorite;
            htmlDescription = Processor.process(publication.getDescription());
        }

        public Publication getPublication() {
            return publication;
        }

        public void setPublication(Publication publication) {
            this.publication = publication;
        }

        public String getHtmlDescription(){
            return htmlDescription;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }
    }
}
