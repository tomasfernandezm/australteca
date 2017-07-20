package org.australteca.dao;

import org.australteca.entity.*;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomi on 02/04/17.
 */
public class UserDAOTester {

    @Test
    public void removeModerator(){
        Subject subject = new Subject("Analisis");
        SubjectDao subjectDao = new SubjectDao();
        Integer subjectId = subjectDao.add(subject);
        UserDao userDao = new UserDao();
        User user = new User("a", "b","aaa", "aaa", "aawe", false, false);
        Integer userID = userDao.add(user);

        subject = subjectDao.get(subjectId);
        user = userDao.get(subjectId);

        SubjectModeratorRelationship smr = new SubjectModeratorRelationship(subject, user);
        SubjectModeratorRelationshipDao smrDao = new SubjectModeratorRelationshipDao();
        smr.accept();
        smrDao.add(smr);

        userDao.delete(userID);

        assertThat(userDao.get(userID)).isNull();
    }
}
