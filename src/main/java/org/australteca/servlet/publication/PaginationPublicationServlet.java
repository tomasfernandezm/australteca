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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 14/07/17.
 */
public class PaginationPublicationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String publicationType = req.getParameter(Constants.PUBLICATION_ROLE);
        String pageNumb = req.getParameter(Constants.PAGE_NUMBER);
        int pageNumber = 1;
        if(pageNumb != null) pageNumber = Integer.parseInt(pageNumb);

        String email = req.getRemoteUser();

        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publicationList = publicationDao.list(publicationType);

        List<Publication> result = new ArrayList<>();

        int numberOfPubs = 0;
        int startIndex = pageNumber*4 - 4;

        while(numberOfPubs < 4 && startIndex + numberOfPubs < publicationList.size()){
            int index = numberOfPubs + startIndex;
            result.add(publicationList.get(index));
            numberOfPubs++;
        }

        List<PublicationWrapper> publicationWrappers = new ArrayList<>();

        for (Publication aResult : result) {
            boolean author = aResult.getAuthor().getEmail().equals(email);
            publicationWrappers.add(new PublicationWrapper(aResult, author));
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson(publicationWrappers));
    }
}
