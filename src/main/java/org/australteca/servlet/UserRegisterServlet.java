package org.australteca.servlet;

import org.australteca.dao.UserDAO;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 05/04/17.
 */

public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String name = request.getParameter(NAME_PARAM);
        String lname = request.getParameter(LAST_NAME_PARAM);
        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String passwordC = request.getParameter(PASSWORD_CONFIRMATION_PARAM);
        String career = request.getParameter(CAREER_PARAM);

        Integer status = new UserDAO().addToDatabase(new User(name, lname, email, career, password, false, false));

        if(status == null) status = -1;

        resp.setContentType("text/html");

        request.getSession().setAttribute(STATUS, status);
        request.getRequestDispatcher("/registerConfirmation.jsp").forward(request, resp);
    }
}
