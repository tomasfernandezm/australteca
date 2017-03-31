package manager;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.Subject;
import entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.function.BiConsumer;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserManager extends EntityManager<User> {

    // pasar User directamente en vez de todos los parámetros. Es más comodo y al mismo tiempo
    // va a posibilitar tirarlo al EntityManager, de ultima se hace un override

    public Integer add(@NotNull String name, @NotNull String lName, @NotNull String email,
                   @NotNull String password, @NotNull String passwordC, @NotNull String career){

        Integer result = null;

        if(checkEmail(email) && checkPassword(password, passwordC)) {
            User userRegister = new User(name, lName, email, career, password, false, false);
            result = addToDatabase(userRegister);
        }
        return result;
    }

    public void addComentary(@NotNull Integer userID, @NotNull Commentary commentary){
        BiConsumer<User, Commentary> biConsumer = (user, comm) ->{
            user.getCommentaries().add(comm);
        };
        modify(User.class, userID, commentary, biConsumer);
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
