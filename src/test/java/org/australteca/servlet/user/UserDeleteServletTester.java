package org.australteca.servlet.user;

import org.australteca.dao.UserDAO;
import org.australteca.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 16/04/17.
 */
public class UserDeleteServletTester extends Mockito {

    @Test
    public void testServlet() throws IOException, ServletException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getRemoteUser()).thenReturn("email");

        UserDAO userDAO = new UserDAO();
        Integer id = userDAO.addToDatabase(new User("P", "G", "email", "ing", "aaaaa", false, false));

        assertThat(userDAO.getUserByEmail("email")).isNotNull();
        assertThat(userDAO.getUser(id)).isNotNull();

        try {
            new UserDeleteServlet().doPost(request, response);
        }catch (NullPointerException e){
            System.out.println(e.getMessage()); // request does not have RequestDispatcher
        }

        assertThat(userDAO.getUser(id)).isNull();
        assertThat(userDAO.getUserByEmail("email")).isNull();
    }
}
