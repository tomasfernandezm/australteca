package org.australteca.demo.generator;

import org.australteca.dao.CommentaryDao;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.demo.constants.CommentaryConstants;
import org.australteca.entity.Commentary;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import java.util.List;

/**
 * Created by tomi on 27/06/17.
 */
public class CommentaryGenerator extends AbstractGenerator{

    private SubjectDao subjectDao = new SubjectDao();
    private UserDao userDao = new UserDao();
    private int numberOfCommentaries = 100;
    CommentaryDao commentaryDao = new CommentaryDao();

    @Override
    public void generate() {

        List<Subject> subjectList = subjectDao.list();
        List<User> userList = userDao.list();

        String[] commentaries = CommentaryConstants.commentaries;

        for(int i = 0;i < numberOfCommentaries;i++){

            double random1 = random.nextDouble();
            String commentary = commentaries[getIndex(0, commentaries.length, random1)];
            User user = userList.get(getIndex(0, userList.size(), random1));
            Subject subject = subjectList.get(getIndex(0, subjectList.size(), random1));

            Commentary commentaryEntity = new Commentary(commentary, user, subject);
            commentaryDao.add(commentaryEntity);

            user.getCommentaries().add(commentaryEntity);
            subject.getCommentaryList().add(commentaryEntity);

            userDao.merge(user);
            subjectDao.merge(subject);
        }
    }


}
