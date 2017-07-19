package org.australteca.dao;

import org.australteca.Constants;
import org.australteca.entity.Publication;
import org.australteca.entity.User;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 19/07/17.
 */
public class PublicationDaoTester {

    @Test
    public void searchTest(){
        User user = new User("a", "b", "a.b@gmail.com", Constants.INGENIERIA_INF_VALUE, "asd", false, false);
        UserDao userDao = new UserDao();
        Integer id = userDao.add(user);

        user = userDao.get(id);

        serializePublications(user);

        PublicationDao publicationDao = new PublicationDao();
        List<Publication> publications = publicationDao.search("ana");

        assertThat(publications).hasSize(3);
        assertThat(publications.get(0).getName()).isEqualToIgnoringCase("analisis 2");
        assertThat(publications.get(1).getName()).isEqualToIgnoringCase("anali 2");
        assertThat(publications.get(2).getName()).isEqualToIgnoringCase("analisi 5");
    }

    private void serializePublications(User user){

        Publication publication1 = new Publication("Analisis 2", user, "aaa", "aaa");
        Publication publication2 = new Publication("Anali 2", user, "aaa", "aaa");
        Publication publication3 = new Publication("Analisi 5", user, "aaa", "aaa");
        Publication publication4 = new Publication("Proyectual", user, "aaa", "aaa");

        PublicationDao publicationDao = new PublicationDao();
        publicationDao.add(publication1);
        publicationDao.add(publication2);
        publicationDao.add(publication3);
        publicationDao.add(publication4);
    }
}
