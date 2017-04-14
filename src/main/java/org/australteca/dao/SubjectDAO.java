package org.australteca.dao;

import com.sun.istack.internal.NotNull;
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
public class SubjectDAO extends AbstractDAO<Subject> {

    public void delete(@NotNull Integer subjectID){
        deleteFromDatabase(Subject.class, subjectID);
    }

    public List<Subject> listSubjects() {
        return listEntities(Subject.class);
    }

    public Subject get(@NotNull final Integer id) {
        return get(Subject.class, id);
    }

    public Subject getByName(@NotNull String name){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
}
