package org.securityfilter.example;

import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.junit.Assert.*;
import org.securityfilter.example.realm.LoginSecurityRealm;
import util.HibernateUtil;

import java.io.Serializable;

/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealmTester {

    private String persistUser(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        String userID = null;
        try{
            tx = session.beginTransaction();
            User user = new User("pepito", "gimenez",
                    "elpepo@ing.austral.edu.ar", "informática", "password", false, false);
            userID = (String) session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userID;
    }

    @Test
    public void booleanAuthenticateTest() {
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        persistUser();
        boolean validate = lsr.booleanAuthenticate("testUsr", "password");
        assertTrue(!validate);
        validate = lsr.booleanAuthenticate("testUser", "password");
        assertTrue(validate);
    }

    @Test
    public void isUserInRoleTest(){
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        persistUser();
        boolean validate = lsr.isUserInRole("testUser", "notThisRole");
        assertTrue(!validate);
        validate = lsr.isUserInRole("testUser", "role");
        assertTrue(validate);
    }
}
