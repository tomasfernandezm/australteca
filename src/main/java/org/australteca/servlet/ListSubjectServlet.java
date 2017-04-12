package org.australteca.servlet;

import org.australteca.dao.SubjectDAO;
import org.australteca.dao.UserDAO;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tomi on 06/04/17.
 */
public class ListSubjectServlet extends HttpServlet{

    String userEmail;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userEmail = req.getRemoteUser();
        List<SubjectWrapper> subjectWrapperList = new ArrayList<>();
        List<Subject> subjectList = new SubjectDAO().listSubjects();

        if(userEmail.equals("")){
            for(Subject s: subjectList){
                subjectWrapperList.add(new SubjectWrapper(s, false));
            }
        }else{
            User user = new UserDAO().getUserByEmail(userEmail);
            Set<Subject> userSubjects = user.getSubjects();
            for(Subject s: subjectList){
                if(userSubjects.contains(s)) subjectWrapperList.add(new SubjectWrapper(s, true));
                else subjectWrapperList.add(new SubjectWrapper(s, false));
            }
        }
        req.setAttribute("subjectWrappers", subjectWrapperList);
        req.getRequestDispatcher("/mainMenu/subject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public class SubjectWrapper{

        Subject subject;
        boolean favorite;

        SubjectWrapper(Subject subject, boolean favorite) {
            this.subject = subject;
            this.favorite = favorite;
        }

        public Subject getSubject() {
            return subject;
        }

        public boolean isFavorite() {
            return favorite;
        }
    }
}
