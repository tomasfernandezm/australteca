package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Note;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class SubjectDao extends AbstractDao<Subject> {

    public void delete(@NotNull Integer subjectID){
        Subject subject = get(subjectID);
        removeAllUsers(subject);
        merge(subject);
        delete(Subject.class, subjectID);
    }

    public List<Subject> list() {
        return list(Subject.class);
    }

    public Subject get(@NotNull final Integer id) {
        return get(Subject.class, id);
    }

    public Subject getByName(@NotNull String name){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from Subject subject where subject.subjectName = :name";
        Transaction tx = null;
        Subject subject = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("name", name);
            subject = (Subject) query.getSingleResult();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return subject;
    }

    private boolean hasUsers(@NotNull Subject subject){
        return subject.getUserList().size() == 0;
    }

    private void removeAllUsers(@NotNull Subject subject){
        if(!subject.getUserList().isEmpty()){
            for(User u: subject.getUserList()) u.getSubjects().remove(subject);
        }
    }
}
