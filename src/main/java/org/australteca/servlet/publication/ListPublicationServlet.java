package org.australteca.servlet.publication;

import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.User;

import javax.persistence.criteria.CriteriaBuilder;
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
        String searchTitle = req.getParameter(Constants.PUBLICATION_NAME);
        String workPageNumberString = req.getParameter(Constants.WORK_PAGE_NUMBER);
        String investPageNumberString = req.getParameter(Constants.INVESTIGATION_PAGE_NUMBER);
        String pubRole = req.getParameter(Constants.PUBLICATION_ROLE);

        Integer workPageNumber;
        Integer investPageNumber;
        if(workPageNumberString == null){
            workPageNumber = 1;
            investPageNumber = 1;
        }
        else{
            workPageNumber = Integer.parseInt(workPageNumberString);
            investPageNumber = Integer.parseInt(investPageNumberString);
        }

        PublicationDao publicationDao = new PublicationDao();

        List<Publication> publications;
        List<Publication> works = new ArrayList<>();
        List<Publication> investigations = new ArrayList<>();
        if(searchTitle == null || searchTitle.equals("")) {
            works = publicationDao.list(Constants.WORK_PUBLICATION);
            investigations = publicationDao.list(Constants.INVESTIGATION_PUBLICATION);
            searchTitle = "";
        } else{
            publications = publicationDao.search(searchTitle);
            for(Publication p: publications){
                if(p.getRole().equals(Constants.WORK_PUBLICATION)) works.add(p);
                else investigations.add(p);
            }
        }

        List<PublicationWrapper> workWrapper = new ArrayList<>();
        List<PublicationWrapper> investWrapper = new ArrayList<>();

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(req.getRemoteUser());

        int workIndex = 4*workPageNumber - 4;
        int investIndex = 4*investPageNumber -4;

        int numberOfWorks = 0;
        int numberOfInvestigations = 0;

        while(workIndex < works.size() && numberOfWorks < 4){
            Publication p = works.get(workIndex);
            if(p.getAuthor().equals(user)) workWrapper.add(new PublicationWrapper(p, true));
            else workWrapper.add(new PublicationWrapper(p, false));
            numberOfWorks++;
            workIndex++;
        }

        while(investIndex < investigations.size() && numberOfInvestigations < 4){
            Publication p = investigations.get(investIndex);
            if(p.getAuthor().equals(user)) investWrapper.add(new PublicationWrapper(p, true));
            else investWrapper.add(new PublicationWrapper(p, false));
            numberOfInvestigations++;
            investIndex ++;
        }

        req.setAttribute(Constants.WORK_PAGE_NUMBER, workPageNumber);
        req.setAttribute(Constants.INVESTIGATION_PAGE_NUMBER, investPageNumber);
        req.setAttribute(Constants.WORK_PUBLICATION_LIST, workWrapper);
        req.setAttribute(Constants.INVESTIGATION_PUBLICATION_LIST, investWrapper);
        req.setAttribute(Constants.SEARCH_PATTERN, searchTitle);

        req.setAttribute(Constants.WORK_TOTAL_PAGES, calculateNumberOfPages(works.size(), numberOfPublicationsPerPage));
        req.setAttribute(Constants.INVESTIGATION_TOTAL_PAGES, calculateNumberOfPages(investigations.size(), numberOfPublicationsPerPage));
        req.getRequestDispatcher("/jsp/work.jsp").forward(req, resp);
    }

    private int calculateNumberOfPages(int listSize, int pubsPerPage){
        if(listSize+1 % pubsPerPage == 0) return listSize/pubsPerPage;
        else return listSize/pubsPerPage + 1;
    }

    private String getOtherRole(String currentRole){
        if(currentRole.equals(Constants.WORK_PUBLICATION)) return Constants.INVESTIGATION_PUBLICATION;
        else return Constants.WORK_PUBLICATION;
    }
}
