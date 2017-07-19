package org.australteca.servlet.publication;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Publication;
import org.australteca.entity.User;
import org.australteca.servlet.publication.PublicationWrapper;
import org.junit.Test;

/**
 * Created by tomi on 18/07/17.
 */
public class PublicationAsGsonTester {

    @Test
    public void test(){

        User user = new User("Delfina", "Manfrino", "delfina.manfrino@ing.austral.edu.ar", "asdfasdfasdf", Constants.INGENIERIA_INF_VALUE, false, false);
        Publication publication = new Publication("sadfasdfasdf", user, "afasdasdasd", "WORK");

        publication.setId(1);

        PublicationWrapper publicationWrapper = new PublicationWrapper(publication, true);

        System.out.println(new Gson().toJson(publicationWrapper));

    }
}
