package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.*;
import org.australteca.utils.HibernateUtil;
import org.australteca.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

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
        removeAllCommentariesFromUsers(subject);
        subject.getCommentaryList().clear();
        removeAllProfessors(subject);
        subject.getProfessors().clear();
        removeAllModerators(subject);
        subject.getModeratorRelationshipList().clear();
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

    private void removeAllUsers(@NotNull Subject subject){
        if(!subject.getUserList().isEmpty()){
            for(User u: subject.getUserList()) {
                u.getSubjects().remove(subject);
                if(u.getSubjectScores().containsKey(subject.getSubjectName())){
                    u.getSubjectScores().remove(subject.getSubjectName());
                }
            }
        }
    }
    private void removeAllCommentariesFromUsers(@NotNull Subject subject){
        if(!subject.getCommentaryList().isEmpty()){
            for(Commentary c: subject.getCommentaryList()){
                User u = c.getAuthor();
                u.getCommentaries().remove(c);

            }
            subject.getCommentaryList().clear();
        }
    }

    private void removeAllProfessors(@NotNull Subject subject){
        for(Professor p: subject.getProfessors()){
            p.getSubjects().remove(subject);
        }
    }

    private void removeAllModerators(Subject subject){
        for(SubjectModeratorRelationship smr: subject.getModeratorRelationshipList()){
            User user = smr.getUser();
            user.getSubjectModeratorRelationships().remove(smr);
        }
    }
}
