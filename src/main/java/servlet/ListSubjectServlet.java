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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getAttribute("userID");
        Set<Subject> subjectSet;
        if(userId == null){
            List<Subject> subjectList = new SubjectDAO().listSubjects();
            subjectSet = new HashSet<>(subjectList);
        }else{
            User user = new UserDAO().getUser(userId);
            subjectSet = user.getSubjects();
        }
        req.setAttribute("subjects", subjectSet);
        req.getRequestDispatcher("a donde lo querramos mandar").forward(req, resp);
    }
}
