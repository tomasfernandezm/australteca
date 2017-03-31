package manager;

import entity.Commentary;
import entity.User;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 30/03/17.
 */
public class CommentaryManagerTester {

    private Commentary giveCommentary(){
        return new Commentary("aaaaa", giveUser());
    }

    private User giveUser(){
        return new User("Pepito", "Rodriguez", "p.rodriguez@ing.austral.edu.ar",
                "Informática", "123456789", false, false);
    }

    @Test
    public void testUpdateCommentary(){

        CommentaryManager commentaryManager = new CommentaryManager();
        UserManager userManager = new UserManager();

        Integer userID = userManager.add("Pepito", "Rodriguez", "p.rodriguez@ing.austral.edu.ar",
                "123456789","123456789", "Informatica");
        User user = userManager.get(User.class, userID);

        userManager.addComentary(userID, new Commentary(giveCommentary().getCommentary(), user));
        //commentaryManager.add(giveCommentary().getCommentary(), user);

        List<Commentary> list = commentaryManager.listEntities(Commentary.class);

        assertThat(commentaryManager.get(list.get(0).getId())).isNotNull();

        assertThat(commentaryManager.get(5)).isNull();
    }
}
