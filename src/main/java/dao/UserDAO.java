package dao;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.Subject;
import entity.User;

import java.util.List;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserDAO extends AbstractDAO<User> {

    public void delete(@NotNull Integer userID) {
        deleteFromDatabase(User.class, userID);

    }

    public List<User> listUsers() {
        return listEntities(User.class);
    }

    public User getUser(@NotNull Integer userID){
        return get(User.class, userID);
    }
}
