package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.australteca.util.HibernateUtil;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by tomasforman on 28/3/17.
 */
public class UserDao extends AbstractDao<User> {

    public void delete(@NotNull Integer userID) {
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
}
