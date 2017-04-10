package servlet;

import dao.SubjectDAO;
import dao.UserDAO;
import entity.Subject;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomi on 06/04/17.
 */
public class ListSubjectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userEmail = (String) req.getAttribute("userEmail");
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
        req.setAttribute("subjects", subjectWrapperList);
        req.getRequestDispatcher("a donde lo querramos mandar").forward(req, resp);
    }

    private class SubjectWrapper{

        Subject subject;
        boolean favorite;

        SubjectWrapper(Subject subject, boolean favorite) {
            this.subject = subject;
            this.favorite = favorite;
        }
    }
}
