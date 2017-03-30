package manager;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

/**
 * Created by tomi on 29/03/17.
 */
public class CommentaryManager extends EntityManager<Commentary> {

    public void add(@NotNull String commentary, @NotNull User author){
        addToDatabase(new Commentary(commentary, author));
    }

    public void delete(@NotNull Integer commentaryID){
        deleteFromDatabase(Commentary.class, commentaryID);
    }

    public void updateCommentary(@NotNull Integer commentaryID, @NotNull String updatedCommentary){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Commentary commentary = (Commentary) session.get(Commentary.class, commentaryID);
            commentary.setCommentary(updatedCommentary);
            commentary.setLastModificationDate(new Date());
            session.update(commentary);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
