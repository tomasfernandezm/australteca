package org.australteca.servlet.command.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.USER_COMMENTARY_LIST;
import static org.australteca.Constants.USER_SUBJECT_LIST;

/**
 * Created by tomi on 26/04/17.
 */
public class PostUserAttributesCommand implements Command{

    @Override
    public void execute(HttpServletContext context) throws IOException, ServletException {
        String email = context.getServletRequest().getRemoteUser();

        UserDao userDAO = new UserDao();
        User user = userDAO.getUserByEmail(email);

        context.getServletRequest().setAttribute(USER_COMMENTARY_LIST, user.getCommentaries());
        context.getServletRequest().setAttribute(USER_SUBJECT_LIST, user.getSubjects());

        context.forwardRequest("/mainMenu/home.jsp");
    }

    @Override
    public Command create() {
        return new PostUserAttributesCommand();
    }
}
