package dao;

import com.sun.istack.internal.NotNull;
import entity.Commentary;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class CommentaryDAO extends AbstractDAO<Commentary> {

    public void delete(@NotNull Integer commentaryID){
        deleteFromDatabase(Commentary.class, commentaryID);
    }

    public Commentary getCommentary(@NotNull Integer id){
        return super.get(Commentary.class, id);
    }

    public List<Commentary> listCommentaries() {
        return listEntities(Commentary.class);
    }
}
