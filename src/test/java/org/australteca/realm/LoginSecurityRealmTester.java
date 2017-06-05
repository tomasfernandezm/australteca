package org.australteca.realm;

import com.sun.istack.internal.NotNull;

import org.australteca.dao.UserDao;
import org.australteca.entity.User;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


/**
 * Created by tomi on 25/03/17.
 */
public class LoginSecurityRealmTester {

    @NotNull
    private User giveUser(){
        return new User("pepito", "gimenez", "elpepo@ing.austral.edu.ar",
                "informática", "password", false, false);
    }

    @Test
    public void getUserByEmailTest(){
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = new UserDao().add(giveUser());
        User user = lsr.getUserByEmail(giveUser().getEmail());
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualToIgnoringCase(giveUser().getEmail());
    }

    @Test
    public void booleanAuthenticateTest() {
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = new UserDao().add(giveUser());
        boolean validate = lsr.booleanAuthenticate("3", "password");
        assertThat(!validate).isTrue();
        validate = lsr.booleanAuthenticate(giveUser().getEmail(), "password");
        assertThat(validate).isTrue();
    }

    @Test
    public void isUserInRoleTest(){
        LoginSecurityRealm lsr = new LoginSecurityRealm();
        Integer userId = new UserDao().add(giveUser());
        boolean validate = lsr.isUserInRole(giveUser().getEmail(), "notThisRole");
        assertThat(!validate).isTrue();
        validate = lsr.isUserInRole(giveUser().getEmail(), "user");
        assertThat(validate).isTrue();
    }
}
