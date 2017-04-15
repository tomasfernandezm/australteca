package org.australteca.servlet;

import org.australteca.Constants;
import org.australteca.dao.UserDAO;
import org.australteca.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.australteca.Constants.INGENIERIA_IND_VALUE;
import static org.australteca.Constants.INGENIERIA_INF_VALUE;
import static org.australteca.Constants.STATUS;

/**
 * Created by tomi on 15/04/17.
 */
public class UserModificationServletTester extends Mockito{
    @Test
    public void testServlet() throws IOException, ServletException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter(Constants.NAME_PARAM)).thenReturn("nameChanged");
        when(request.getParameter(Constants.LAST_NAME_PARAM)).thenReturn("lastNameChanged");
        when(request.getParameter(Constants.EMAIL_PARAM)).thenReturn("emailChanged");
        when(request.getParameter(Constants.PASSWORD_PARAM)).thenReturn("passwordChanged");
        when(request.getParameter(Constants.PASSWORD_CONFIRMATION_PARAM)).thenReturn("passwordChanged");
        when(request.getParameter(Constants.CAREER_PARAM)).thenReturn(INGENIERIA_IND_VALUE);
        when(request.getRemoteUser()).thenReturn("email");

        UserDAO userDAO = new UserDAO();
        userDAO.addToDatabase(new User("name", "lastName", "email", INGENIERIA_INF_VALUE, "password", false, false));

        try {
            new UserModificationServlet().doPost(request, response);
        }catch (NullPointerException e){
            System.out.println(e.getMessage()); // request does not have RequestDispatcher
        }

        User user = userDAO.getUserByEmail("emailChanged");

        assertThat(user.getFirstName()).isEqualTo("nameChanged");
        assertThat(user.getLastName()).isEqualTo("lastNameChanged");
        assertThat(user.getEmail()).isEqualTo("emailChanged");
        assertThat(user.getPassword()).isEqualTo("passwordChanged");
        assertThat(user.getCourse()).isEqualTo(INGENIERIA_IND_VALUE);
        assertThat(request.getAttribute(STATUS)).isNotEqualTo(-1);

    }

}
