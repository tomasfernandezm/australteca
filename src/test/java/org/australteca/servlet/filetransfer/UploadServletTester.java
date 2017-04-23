package org.australteca.servlet.filetransfer;

import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 21/04/17.
 */
public class UploadServletTester extends Mockito {

    @Test
    public void testServlet() throws IOException, ServletException{

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter(SUBJECT_NAME_PARAM)).thenReturn("subjectName");
        when(request.getParameter(NOTE_NAME_PARAM)).thenReturn("note");
        when(request.getParameter(NOTE_TYPE_PARAM)).thenReturn("type");
        when(request.getMethod()).thenReturn("post");
        when(request.getRemoteUser()).thenReturn("user");

        UserDao userDao = new UserDao();
        userDao.add(new User("name", "lname", "user", INGENIERIA_INF_VALUE, "password", false, false));

        SubjectDao subjectDao = new SubjectDao();
        subjectDao.add(new Subject("subject"));

        new UploadServlet().doPost(request, response);

    }
}
