package servlet;

import dao.SubjectDAO;
import entity.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 08/04/17.
 */
public class AddSubjectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectName = (String) req.getAttribute("subjectName");
        SubjectDAO subjectDAO = new SubjectDAO();

        subjectDAO.addToDatabase(new Subject(subjectName));
    }
}
