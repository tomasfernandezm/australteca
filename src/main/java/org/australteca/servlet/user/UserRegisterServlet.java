package org.australteca.servlet.user;

import org.australteca.Constants;
import org.australteca.dao.UserDao;
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
        Integer status = 0;

        if(!password.equals(passwordC)){
            status = 1;
        }else {
            Integer userID;

            if(email.equals(Constants.ADMIN_USERNAME_1) || email.equals(Constants.ADMIN_USERNAME_2)){
                userID = new UserDao().add(new User(name, lname, email, career, password, false, true));
            }else {
                userID = new UserDao().add(new User(name, lname, email, career, password, false, false));
            }
            if(userID == null) status = 2;
        }
        request.setAttribute(STATUS, status);
        request.getRequestDispatcher("/jsp/registerConfirmation.jsp").forward(request, resp);
    }
}
