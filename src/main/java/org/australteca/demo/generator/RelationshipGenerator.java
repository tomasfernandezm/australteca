package org.australteca.demo.generator;

import org.australteca.dao.SubjectDao;
import org.australteca.dao.UserDao;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import java.util.List;
import java.util.Random;

/**
 * Created by tomi on 14/07/17.
 */
public class RelationshipGenerator implements Generator{

    private UserDao userDao = new UserDao();
    private SubjectDao subjectDao = new SubjectDao();
    private int numberOfSURelationships;

    public RelationshipGenerator(int numberOfSubjectUser){
        numberOfSURelationships = numberOfSubjectUser;
    }

    @Override
    public void generate() {
        generateUserSubjectRelationship(numberOfSURelationships);
    }

    void generateUserSubjectRelationship(int n){
        List<User> users = userDao.list();
        List<Subject> subjects = subjectDao.list();

        Random randomGenerator = new Random();
        for(int i = 0;i < n; i++){

            double random = randomGenerator.nextDouble();
            int userIndex = (int) Math.floor(users.size()*random);
            int subjectIndex = (int) Math.floor(subjects.size()*random);

            User user = users.get(userIndex);
            Subject subject = subjects.get(subjectIndex);

            user.getSubjects().add(subject);
            subject.getUserList().add(user);
            user.getSubjectScores().put(subject.getSubjectName(), (int) random*5);

            userDao.merge(user);
            subjectDao.merge(subject);
        }
    }
}
