package manager;

import com.sun.istack.internal.NotNull;
import entity.User;

import javax.persistence.EntityNotFoundException;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserManager extends EntityManager<User> {

    public void add(@NotNull String name, @NotNull String lName, @NotNull String email,
                   @NotNull String password, @NotNull String passwordC, @NotNull String career){

        if(checkEmail(email) && checkPassword(password, passwordC)) {
            User userRegister = new User(name, lName, email, career, password, false, false);
            addToDatabase(userRegister);
        }
    }

    public void modify(){

    }

    public void delete(@NotNull Integer userID){
        deleteFromDatabase(User.class, userID);
    }

    private boolean checkPassword(@NotNull String p1, @NotNull String p2){
        return p1.equals(p2);
    }

    private boolean checkEmail(String email){
        return true;
    }
}
