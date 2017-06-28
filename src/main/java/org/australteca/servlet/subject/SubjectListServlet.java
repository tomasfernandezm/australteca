package org.australteca.servlet.subject;

import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
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
public class SubjectListServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = req.getRemoteUser();
        List<SubjectWrapper> subjectWrapperList = new ArrayList<>();
        List<Subject> subjectList = new SubjectDao().list();

        if(userEmail.equals("")){
            for(Subject s: subjectList){
                subjectWrapperList.add(new SubjectWrapper(s, false));
            }
        }else{
            User user = new UserDao().getUserByEmail(userEmail);
            Set<Subject> userSubjects = user.getSubjects();
            for(Subject s: subjectList){
                if(userSubjects.contains(s)) subjectWrapperList.add(new SubjectWrapper(s, true));
                else subjectWrapperList.add(new SubjectWrapper(s, false));
            }
        }
        req.setAttribute("subjectWrappers", subjectWrapperList);
        req.getRequestDispatcher("/jsp/subjects.jsp").forward(req, resp);
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
