package org.australteca.servlet.command.user;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.httpcontext.HttpContext;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by tomi on 26/04/17.
 */
public class DeleteUserCommand implements Command {

    @Override
    public void execute(HttpContext context) throws IOException, ServletException {

        String email = context.getServletRequest().getRemoteUser();
        UserDao userDAO = new UserDao();

        User user = userDAO.getUserByEmail(email);
        Integer userID = user.getId();

        userDAO.delete(userID);

        context.forwardRequest("/logout.jsp");
    }

    @Override
    public Command create() {
        return new DeleteUserCommand();
    }
}
