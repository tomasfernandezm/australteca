package org.securityfilter.example;

import com.sun.istack.internal.NotNull;
import entity.EntityConstants;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.securityfilter.example.realm.LoginSecurityRealm;
import util.HibernateUtil;


/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealmTester {

    @NotNull
    private User giveUser(){
        return new User("pepito", "gimenez", "elpepo@ing.austral.edu.ar",
                "informática", "password", false, false);
    }

    private Integer persistUser(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Integer userID = null;
        try{
            tx = session.beginTransaction();
            User user = giveUser();
            userID = (Integer) session.save(user);
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
    public void getUserByEmailTest(){
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = persistUser();
        User user = lsr.getUserByEmail(giveUser().getEmail());
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualToIgnoringCase(giveUser().getEmail());
    }

    @Test
    public void booleanAuthenticateTest() {
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = persistUser();
        boolean validate = lsr.booleanAuthenticate("5", "password");
        assertThat(!validate).isTrue();
        validate = lsr.booleanAuthenticate(userId.toString(), "password");
        assertThat(validate).isTrue();
    }

    @Test
    public void isUserInRoleTest(){
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = persistUser();
        boolean validate = lsr.isUserInRole("7", "notThisRole");
        assertThat(!validate).isTrue();
        validate = lsr.isUserInRole(userId.toString(), EntityConstants.STANDARD);
        assertThat(validate).isTrue();
    }
}
