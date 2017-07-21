package org.australteca.demo.generator;

import org.australteca.Constants;
import org.australteca.dao.PublicationDao;
import org.australteca.dao.UserDao;
import org.australteca.demo.constants.PublicationConstants;
import org.australteca.entity.Publication;
import org.australteca.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.australteca.demo.constants.PublicationConstants.*;

/**
 * Created by tomi on 27/06/17.
 */
public class PublicationGenerator extends AbstractGenerator {

    private PublicationDao publicationDao = new PublicationDao();
    private UserDao userDao = new UserDao();
    private Random randomGenerator = new Random();

    @Override
    public void generate() {

        List<User> users = userDao.list();
        List<User> myUsers = new ArrayList<>();

        for(int i = 0;i < 7; i++){
            int index = (int) Math.floor(users.size()/randomGenerator.nextDouble());
            myUsers.add(users.get(i));
        }
        generatePublication("Bandit", myUsers.get(0), description1, Constants.WORK_PUBLICATION);
        generatePublication("Mercado Libre", myUsers.get(1), description2, Constants.WORK_PUBLICATION);
        generatePublication("fizzmod", myUsers.get(2), description3, Constants.WORK_PUBLICATION);
        generatePublication("Accenture", myUsers.get(3), description4, Constants.WORK_PUBLICATION);
        generatePublication("La Nación", myUsers.get(4), description5, Constants.WORK_PUBLICATION);
        generatePublication("Grifols", myUsers.get(5), investDescription1, Constants.INVESTIGATION_PUBLICATION);
        generatePublication("Universidad Loyola AndaLucía", myUsers.get(6), investDescription2, Constants.INVESTIGATION_PUBLICATION);
    }

    private void generatePublication(String title, User user, String description, String role){
        Publication p = new Publication(title, user, description, role);
        publicationDao.add(p);
    }

}
