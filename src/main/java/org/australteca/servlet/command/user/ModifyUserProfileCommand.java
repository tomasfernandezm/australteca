package org.australteca.servlet.command.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 26/04/17.
 */
public class ModifyUserProfileCommand implements Command {

    @Override
    public void execute(HttpServletContext context) throws IOException, ServletException {

        String name = context.getServletRequest().getParameter(NAME_PARAM);
        String lname = context.getServletRequest().getParameter(LAST_NAME_PARAM);
        String email = context.getServletRequest().getParameter(EMAIL_PARAM);
        String password = context.getServletRequest().getParameter(PASSWORD_PARAM);
        String passwordC = context.getServletRequest().getParameter(PASSWORD_CONFIRMATION_PARAM);
        String career = context.getServletRequest().getParameter(CAREER_PARAM);

        Integer status;
        if((password == null && passwordC != null) || (password != null && passwordC == null)){
            status = 1;
        }else if(password != null && passwordC != null && !password.equals(passwordC)){
            status = 2;
        }else {
            UserDao userDAO = new UserDao();
            User user = userDAO.getUserByEmail(context.getServletRequest().getRemoteUser());

            if(!name.equals("")) user.setFirstName(name);
            if(!lname.equals("")) user.setLastName(lname);
            if(!email.equals(""))user.setEmail(email);
            if(!password.equals(""))user.setPassword(password);
            if(!career.equals(""))user.setCourse(career);

            userDAO.merge(user);
            status = 0;
        }
        if(status == 0) context.redirect("/logout.jsp");
        else {
            context.getServletRequest().setAttribute(STATUS, status);
            context.forwardRequest("/userPost");
        }
    }

    @Override
    public Command create() {
        return new ModifyUserProfileCommand();
    }
}
