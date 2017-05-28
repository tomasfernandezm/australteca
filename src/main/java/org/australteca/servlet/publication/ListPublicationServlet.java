package org.australteca.servlet.publication;

import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.entity.Publication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

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

        List<Publication> investigations = new ArrayList<>();
        List<Publication> works = new ArrayList<>();

        for(Publication p: publications){
            if(p.getRole().equals(Constants.WORK_PUBLICATION)) works.add(p);
            else investigations.add(p);
        }

        req.setAttribute(Constants.WORK_PUBLICATION_LIST, works);
        req.setAttribute(Constants.INVESTIGATION_PUBLICATION_LIST, investigations);

        req.getRequestDispatcher("/mainMenu/work.jsp").forward(req, resp);
    }
}
