package org.australteca.demo.generator;

import org.australteca.dao.UserDao;
import org.australteca.demo.constants.UserConstants;
import org.australteca.entity.User;

import java.util.Random;

/**
 * Created by tomi on 27/06/17.
 */
public class UserGenerator extends AbstractGenerator {

    private int numberOfUsers;

    UserGenerator(){
        numberOfUsers = 100;
    }

    public UserGenerator(int numberOfUsers){
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public void generate() {

        UserDao userDao = new UserDao();

        String[] names = UserConstants.names;
        String[] lastNames = UserConstants.lastNames;
        String[] passwords = UserConstants.passwords;
        String[] careers = UserConstants.careers;

        for(int i = 0;i < numberOfUsers; i++){

            double random1 = random.nextDouble();
            String fn = names[getIndex(0, names.length, random1)];
            String ln = names[getIndex(0, lastNames.length, random1)];
            String p = names[getIndex(0, passwords.length, random1)];
            String c = names[getIndex(0, careers.length, random1)];

            User user = new User(fn, ln, makeEmail(fn, ln), c, p, false, false);
            userDao.add(user);
        }

    }

    String makeEmail(String name, String lastName){

        String fn = name.toLowerCase();
        String ln = lastName.toLowerCase();

        return fn + "." + ln + "@ing.austral.edu.ar";
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }
}
