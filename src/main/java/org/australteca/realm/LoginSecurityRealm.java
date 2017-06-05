package org.australteca.realm;

import com.sun.istack.internal.NotNull;
import org.australteca.Constants;
import org.australteca.dao.UserDao;
import org.australteca.entity.User;
import org.securityfilter.realm.SimpleSecurityRealmBase;

/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealm extends SimpleSecurityRealmBase {

    @Override
    public boolean booleanAuthenticate(String username, String password) {
        User user = getUserByEmail(username);
        if(username.equals(Constants.ADMIN_USERNAME) && password.equals(Constants.ADMIN_PASSWORD)) return true;
        return user != null && user.getEmail().equals(username) && user.getPassword().equals(password);
    }

    public User getUserByEmail(@NotNull String email){
        return new UserDao().getUserByEmail(email);
    }

    @Override
    public boolean isUserInRole(String username, String role) {
        User user = getUserByEmail(username);
        return user != null && user.getEmail().equals(username) && user.getRole().equals(role);
    }
}
