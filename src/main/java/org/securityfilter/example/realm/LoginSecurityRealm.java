package org.securityfilter.example.realm;

import com.sun.istack.internal.NotNull;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealm extends TrivialSecurityRealm{

    @Override
    public boolean booleanAuthenticate(String username, String password) {
        User user = getUser(username);
        if(user != null && user.getEmail().equals(username) && user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    private User getUser(@NotNull String userID){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx =  null;
        User user = null;
        try{
            tx = session.beginTransaction();
            user = session.get(User.class, userID);
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    @Override
    public boolean isUserInRole(String username, String role) {
        User user = getUser(username);
        return user.equals(username) && user.getRole().equals(role);
    }
}
