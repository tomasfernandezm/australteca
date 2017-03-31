package manager;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * Created by tomi on 29/03/17.
 */
public class CommentaryManager extends EntityManager<Commentary> {

    public Integer add(@NotNull String commentary, @NotNull User author){
        return addToDatabase(new Commentary(commentary, author));
    }

    public void delete(@NotNull Integer commentaryID){
        deleteFromDatabase(Commentary.class, commentaryID);
    }

    public Commentary get(@NotNull Integer id){
        return super.get(Commentary.class, id);
    }

    public void updateCommentary(@NotNull Integer commentaryID, @NotNull String updatedCommentary){
        BiConsumer<Commentary, String> biConsumer = (commentary, update) ->{
            commentary.setCommentary(update);
            commentary.setLastModificationDate(new Date());
        };
        modify(Commentary.class, commentaryID, updatedCommentary, biConsumer);
    }
}
