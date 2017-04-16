package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.UserDAO;
import org.australteca.entity.User;
import org.australteca.servlet.user.UserRegisterServlet;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;
import static org.australteca.Constants.INGENIERIA_INF_VALUE;
import static org.australteca.Constants.STATUS;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 15/04/17.
 */
public class UserRegisterServletTester extends Mockito{

    @Test
    public void testServlet() throws IOException, ServletException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter(Constants.NAME_PARAM)).thenReturn("name");
        when(request.getParameter(Constants.LAST_NAME_PARAM)).thenReturn("lastName");
        when(request.getParameter(Constants.EMAIL_PARAM)).thenReturn("email");
        when(request.getParameter(Constants.PASSWORD_PARAM)).thenReturn("password");
        when(request.getParameter(Constants.PASSWORD_CONFIRMATION_PARAM)).thenReturn("password");
        when(request.getParameter(Constants.CAREER_PARAM)).thenReturn(INGENIERIA_INF_VALUE);

        try {
            new UserRegisterServlet().doPost(request, response);
        }catch (NullPointerException e){
            System.out.println(e.getMessage()); // request does not have RequestDispatcher
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByEmail("email");

        assertThat(user.getFirstName()).isEqualTo("name");
        assertThat(user.getLastName()).isEqualTo("lastName");
        assertThat(user.getEmail()).isEqualTo("email");
        assertThat(user.getPassword()).isEqualTo("password");
        assertThat(user.getCourse()).isEqualTo(INGENIERIA_INF_VALUE);
        assertThat(request.getAttribute(STATUS)).isNotEqualTo(-1);
    }
}
