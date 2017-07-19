package org.australteca.servlet.publication;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.servlet.subject.SubjectDeleteServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.australteca.Constants.INGENIERIA_INF_VALUE;
import static org.australteca.Constants.PUBLICATION_ROLE;

/**
 * Created by tomi on 19/07/17.
 */
public class PublicationPaginationTester extends Mockito {

    @Test
    public void testPagination() throws IOException, ServletException{

        serializePublications();
        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publications = publicationDao.list("Trabajo");

        List<Publication> providedPublications = providePublications(1);

        assertThat(providedPublications).hasSize(4);
        for(int i = 0;i < 4;i++){
            assertThat(publications.get(i)).isEqualToComparingFieldByField(providedPublications.get(i));
        }

        providedPublications = providePublications(2);
        assertThat(providedPublications).hasSize(2);
        for(int i = 0;i < 2;i++){
            assertThat(publications.get(i+4)).isEqualToComparingFieldByField(providedPublications.get(i));
        }
    }

    public List<Publication> providePublications(int pageNumber){

        String publicationType = "Trabajo";

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

        return result;
    }

    public User getUser(){
        return new User("a", "b", "a.b@gmail.com", INGENIERIA_INF_VALUE, "asd", false, false);
    }

    public void serializePublications(){

        UserDao userDao = new UserDao();
        Integer userID = userDao.add(getUser());
        User user = userDao.get(userID);
        PublicationDao publicationDao = new PublicationDao();
        for(int i = 0;i< 6;i++){
            Publication p = new Publication("a", user, "b", "Trabajo");
            publicationDao.add(p);
        }
    }
}
