package org.australteca.servlet;

import org.australteca.dao.UserDAO;
import org.australteca.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tomi on 05/04/17.
 */

public class UserRegisterServlet extends HttpServlet {

    public static final String NAME_PARAM = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String name = request.getParameter(NAME_PARAM);
        String lname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordC = request.getParameter("passwordC");
        String career = request.getParameter("career");

        Integer status = new UserDAO().addToDatabase(new User(name, lname, email, career, password, false, false));

        if(status == null) status = -1;

        resp.setContentType("text/html");

        request.getSession().setAttribute("id", status);
        request.getRequestDispatcher("/registerConfirmation.jsp").forward(request, resp);
    }
}
