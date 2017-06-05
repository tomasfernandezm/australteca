package org.australteca.servlet.command.user;

import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.servlet.command.Command;
import org.australteca.servlet.command.context.http.HttpServletContext;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 26/04/17.
 */
public class ModifyUserSubjectCommand implements Command{

    @Override
    public void execute(HttpServletContext context) throws IOException, ServletException {
        String status = context.getServletRequest().getParameter(FAVORITE_PARAM);
        String subjectName = context.getServletRequest().getParameter(SUBJECT_NAME_PARAM);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(context.getServletRequest().getRemoteUser());

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.getByName(subjectName);

        if(status.equals(MAKE_FAVORITE)){
            user.getSubjects().add(subject);
            subject.getUserList().add(user);
        }else{
            user.getSubjects().remove(subject);
            subject.getUserList().remove(user);
        }

        subjectDao.merge(subject);
        userDao.merge(user);

        context.redirect("/listSubjects");
    }

    @Override
    public Command create() {
        return new ModifyUserSubjectCommand();
    }
}
