package org.australteca.servlet.command.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 26/04/17.
 */
public class PostUserProfileCommand implements Command {

    @Override
    public void execute(HttpServletContext context) throws IOException, ServletException {
        String email = context.getServletRequest().getRemoteUser();
        UserDao userDAO = new UserDao();

        User user = userDAO.getUserByEmail(email);

        context.getServletRequest().setAttribute(NAME_PARAM, user.getFirstName());
        context.getServletRequest().setAttribute(LAST_NAME_PARAM, user.getLastName());
        context.getServletRequest().setAttribute(EMAIL_PARAM, user.getEmail());
        context.getServletRequest().setAttribute(CAREER_PARAM, user.getCourse());
        context.getServletRequest().setAttribute(ROLE_PARAM, user.getRole());

        context.forwardRequest("/mainMenu/userSettings.jsp");
    }

    @Override
    public Command create() {
        return new PostUserProfileCommand();
    }
}
