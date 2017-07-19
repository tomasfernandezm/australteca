package org.australteca.entity;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.junit.Test;

/**
 * Created by tomi on 19/07/17.
 */
public class PublicationTester {

    @Test
    public void JSONParsingTest(){

        User user = new User("Delfina", "Manfrino", "delfina.manfrino@ing.austral.edu.ar", "asdfasdfasdf", Constants.INGENIERIA_INF_VALUE, false, false);
        Publication publication = new Publication("sadfasdfasdf", user, "afasdasdasd", "WORK");

        publication.setId(1);

        System.out.println(new Gson().toJson(publication));

    }
}
