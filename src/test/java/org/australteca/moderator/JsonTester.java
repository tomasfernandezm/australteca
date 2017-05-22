package org.australteca.moderator;

import com.google.gson.GsonBuilder;
import org.australteca.dao.SubjectDao;
import org.australteca.dao.SubjectModeratorRelationshipDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.entity.User;
import org.junit.Test;

import java.util.List;

/**
 * Created by tomi on 21/05/17.
 */
public class JsonTester {

    @Test
    public void testJsonPrint(){

        User user = new User("A", "b", "aabb", "asfasdf", "ADADASD", false, false);

        User user2 = new User("A", "b", "aabb", "asfasdf", "ADADASD", false, false);

        Subject subject1 = new Subject("asdasdas");
        Subject subject2 = new Subject("dafasdf");

        UserDao userDao = new UserDao();
        Integer user1Int = userDao.add(user);
        Integer user2Int = userDao.add(user2);

        SubjectDao subjectDao = new SubjectDao();
        Integer subject1Int = subjectDao.add(subject1);
        Integer subject2Int = subjectDao.add(subject2);



        SubjectModeratorRelationship smr1 = new SubjectModeratorRelationship(subjectDao.get(subject1Int), userDao.get(user1Int));
        SubjectModeratorRelationship smr2 = new SubjectModeratorRelationship(subjectDao.get(subject2Int), userDao.get(user2Int));

        SubjectModeratorRelationshipDao smrd = new SubjectModeratorRelationshipDao();
        smrd.add(smr1);
        smrd.add(smr2);

        List<SubjectModeratorRelationship> smrList = smrd.getByAcceptance(false);
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(smrList));

    }
}
