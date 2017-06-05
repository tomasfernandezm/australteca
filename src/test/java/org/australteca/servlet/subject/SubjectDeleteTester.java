package org.australteca.servlet.subject;

import org.australteca.Constants;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.servlet.user.UserDeleteServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 23/04/17.
 */
public class SubjectDeleteTester extends Mockito {

    @Test
    public void deleteSubjectWithoutUsers() throws IOException, ServletException{

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String subjectName = persistEmptySubject();

        when(request.getParameter(Constants.SUBJECT_NAME_PARAM)).thenReturn(subjectName);

        try {
            new SubjectDeleteServlet().doPost(request, response);
        }catch (NullPointerException e){
            System.out.println(e.getMessage()); // request does not have RequestDispatcher
        }

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);
        assertThat(subject).isNull();
    }

    @Test
    public void deleteSubjectWithUsers() throws IOException, ServletException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        String subjectName = persistEmptySubject();

        when(request.getParameter(Constants.SUBJECT_NAME_PARAM)).thenReturn(subjectName);

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);
        addUsersToSubject(subject);
        subjectDao.merge(subject);

        try {
            new SubjectDeleteServlet().doPost(request, response);
        }catch (NullPointerException e){
            System.out.println(e.getMessage()); // request does not have RequestDispatcher
        }


        subject = subjectDao.getByName(subjectName);
        assertThat(subject).isNull();

    }

    private void addUsersToSubject(Subject subject){
        UserDao userDao = new UserDao();

        for(Integer i = 0;i< 5; i++){
            String number = i.toString();
            User user = new User(number, number, number, Constants.INGENIERIA_INF_VALUE,
                    number, false, false);
            userDao.add(user);
            subject.getUserList().add(userDao.getUserByEmail(number));
        }
    }

    private String persistEmptySubject(){
        Subject subject = new Subject("Subject");
        SubjectDao subjectDao = new SubjectDao();
        subjectDao.add(subject);
        return subject.getSubjectName();
    }
}
