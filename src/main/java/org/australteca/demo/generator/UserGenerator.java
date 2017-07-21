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
    private UserDao userDao = new UserDao();
    private int numberOfUsersAdded = 0;

    public UserGenerator(){
        numberOfUsers = 100;
    }

    public UserGenerator(int numberOfUsers){
        this.numberOfUsers = numberOfUsers;
    }

    @Override
    public void generate() {

        String[] names = UserConstants.names;
        String[] lastNames = UserConstants.lastNames;
        String[] passwords = UserConstants.passwords;
        String[] careers = UserConstants.careers;

        for(int i = 0;i < numberOfUsers; i++){

            double random1 = random.nextDouble();
            String fn = names[getIndex(0, names.length, random1)];
            String ln = lastNames[getIndex(0, lastNames.length, random1)];
            String p = passwords[getIndex(0, passwords.length, random1)];
            String c = careers[getIndex(0, careers.length, random1)];

            User user = new User(fn, ln, makeEmail(fn, ln), c, p, false, false);
            if(!checkIfExists(user)) {
                userDao.add(user);
                numberOfUsersAdded++;
            }
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

    private boolean checkIfExists(User user){
        return userDao.getUserByEmail(user.getEmail()) != null;
    }

    public int getNumberOfUsersAdded() {
        return numberOfUsersAdded;
    }
}
