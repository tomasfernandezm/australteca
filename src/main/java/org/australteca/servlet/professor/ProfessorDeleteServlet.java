package org.australteca.servlet.professor;

import com.google.gson.Gson;
import org.australteca.Constants;
import org.australteca.dao.ProfessorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 04/06/17.
 */
public class ProfessorDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String professorIDString = req.getParameter(Constants.PROFESSOR_ID_PARAM);
        Integer professorID = null;
        if(professorIDString != null) professorID = Integer.parseInt(professorIDString);

        if(professorID != null){
            ProfessorDao professorDao = new ProfessorDao();
            professorDao.delete(professorID);

            resp.setContentType("application/json");
            resp.getWriter().write(new Gson().toJson("OK"));
        }
    }
}
