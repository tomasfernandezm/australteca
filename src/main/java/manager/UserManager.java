package manager;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.Subject;
import entity.User;

import java.util.List;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserManager extends AbstractManager<User> {


    public Integer add(User user) {
        return addToDatabase(user);
    }

    public void delete(@NotNull Integer userID){
        deleteFromDatabase(User.class, userID);
    }

    public void addComentary(@NotNull User user, @NotNull String commentary, @NotNull Subject subject){
        user.getCommentaries().add(new Commentary(commentary, user, subject));
        update(user);
    }

    public void removeComentary(@NotNull User user, @NotNull Commentary commentary){
        user.getCommentaries().remove(commentary);
        update(user);
    }

    public void addSubject(@NotNull User user, @NotNull Subject subject){
        user.getSubjects().add(subject);
        update(user);
    }

    public void removeSubject(@NotNull User user, @NotNull Subject subject){
        user.getSubjects().remove(subject);
        update(user);
    }

    public List<User> listUsers() {
        return listEntities(User.class);
    }

    public User getUser(@NotNull Integer userID){
        return get(User.class, userID);
    }

}
