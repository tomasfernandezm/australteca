package org.securityfilter.example.realm;

import com.sun.istack.internal.NotNull;
import dao.UserDAO;
import entity.User;
import org.hibernate.Session;
import util.HibernateUtil;

import javax.persistence.*;

/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealm extends TrivialSecurityRealm{

    @Override
    public boolean booleanAuthenticate(String username, String password) {
        User user = getUserByEmail(username);
        return user != null && user.getEmail().equals(username) && user.getPassword().equals(password);
    }

    public User getUserByEmail(@NotNull String email){
        return new UserDAO().getUserByEmail(email);
    }

    @Override
    public boolean isUserInRole(String username, String role) {
        User user = getUserByEmail(username);
        return user != null && user.getEmail().equals(username) && user.getRole().equals(role);
    }
}
