package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Commentary;
import org.australteca.entity.Publication;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.australteca.utils.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserDao extends AbstractDao<User> {

    public void delete(@NotNull Integer userID) {
        User user = get(userID);
        removeAllCommentariesFromSubject(user);
        user.getCommentaries().clear();
        removeAllPublications(user);
        user.getPublications().clear();
        merge(user);
        delete(User.class, userID);

    }

    public List<User> list() {
        return list(User.class);
    }

    public User get(@NotNull Integer userID){
        return get(User.class, userID);
    }

    public User getUserByEmail(@NotNull String email){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from User user where user.email = :email";
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("email", email);
            user = (User) query.getSingleResult();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return user;
    }

    private void removeAllPublications(User user) {
        PublicationDao publicationDao = new PublicationDao();
        publicationDao.deleteAll(user.getId());
    }

    private void removeAllCommentariesFromSubject(User user){
        for(Commentary c: user.getCommentaries()){
            Subject s = c.getSubject();
            s.getCommentaryList().remove(c);
        }
    }
}
