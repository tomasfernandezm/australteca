package org.australteca.demo.generator;

import org.australteca.demo.generator.UserGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 12/07/17.
 */
public class UserGeneratorTest {

    @Test
    public void generationTest(){

        UserGenerator userGenerator = new UserGenerator();
        userGenerator.generate();

       /* UserDao userDao = new UserDao();

        List<User> userList = userDao.list();

        assertThat(userList).hasSize(userGenerator.getNumberOfUsers());*/
    }

    @Test
    public void testEmailMaker(){

        UserGenerator userGenerator = new UserGenerator();
        String email = userGenerator.makeEmail("tomas", "fernandez");

        assertThat(email).isEqualTo("tomas.fernandez@ing.austral.edu.ar");
    }

    @Test
    public void testIndex(){

        UserGenerator userGenerator = new UserGenerator();

        int low = 0;
        int high = 100;
        double percentage = 0.675;

        int index = userGenerator.getIndex(low, high, percentage);

        assertThat(index).isEqualTo(67);
    }
}
