package org.australteca.servlet.command.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 26/04/17.
 */
public class RegisterUserCommand implements Command {

    @Override
    public void execute(HttpContext context) throws IOException, ServletException {
        String name = context.getServletRequest().getParameter(NAME_PARAM);
        String lname = context.getServletRequest().getParameter(LAST_NAME_PARAM);
        String email = context.getServletRequest().getParameter(EMAIL_PARAM);
        String password = context.getServletRequest().getParameter(PASSWORD_PARAM);
        String passwordC = context.getServletRequest().getParameter(PASSWORD_CONFIRMATION_PARAM);
        String career = context.getServletRequest().getParameter(CAREER_PARAM);

        Integer status = new UserDao().add(new User(name, lname, email, career, password, false, false));

        if(status == null) status = -1;

        context.getServletRequest().setAttribute(STATUS, status);
        context.forwardRequest("/registerConfirmation.jsp");
    }

    @Override
    public Command create() {
        return new RegisterUserCommand();
    }
}
