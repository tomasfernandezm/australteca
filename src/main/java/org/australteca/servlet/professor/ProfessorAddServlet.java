package org.australteca.servlet.professor;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.ProfessorDao;
import org.australteca.entity.Professor;
import org.australteca.servlet.ModeratorChecker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomasforman on 16/5/17.
 */
public class ProfessorAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter(Constants.PROFESSOR_NAME_PARAM);
        String lastName = req.getParameter(Constants.PROFESSOR_LAST_NAME_PARAM);
        String email = req.getParameter(Constants.PROFESSOR_EMAIL_PARAM);
        String information = req.getParameter(Constants.PROFESSOR_INFORMATION_PARAM);

        ProfessorDao professorDao = new ProfessorDao();

        Integer status = professorDao.add(new Professor(name, lastName, email, information));

        resp.setContentType("application/json");
        resp.getWriter().write(new Gson().toJson("OK"));
    }
}
