package org.australteca.dao;

import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by tomi on 10/05/17.
 */
public class SubjectModeratorRelationshipDao extends AbstractDao<SubjectModeratorRelationship> {


    @Override
    public void delete(Integer id) throws IllegalArgumentException {
        super.delete(SubjectModeratorRelationship.class, id);
    }

    @Override
    public SubjectModeratorRelationship get(Integer id) {
        return super.get(SubjectModeratorRelationship.class, id);
    }

    @Override
    public List<SubjectModeratorRelationship> list() {
        return super.list(SubjectModeratorRelationship.class);
    }

    public SubjectModeratorRelationship getByUserEmailAndSubjectName(String userEmail, String subjectName){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from SubjectModeratorRelationship s where s.user.email = :userEmail and s.subject.subjectName = :subjectName";
        Transaction tx = null;
        SubjectModeratorRelationship smr = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("userEmail", userEmail).setParameter("subjectName", subjectName);
            smr = (SubjectModeratorRelationship) query.getSingleResult();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return smr;
    }

    public List<SubjectModeratorRelationship> getByAcceptance(boolean accepted){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from SubjectModeratorRelationship s where s.accepted = :accepted";
        Transaction tx = null;
        List<SubjectModeratorRelationship> smrList = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("accepted", accepted);
            smrList = (List<SubjectModeratorRelationship>) query.getResultList();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return smrList;
    }

    public List<SubjectModeratorRelationship> getModeratorsBySubject(String subjectName){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from SubjectModeratorRelationship s where s.subject.subjectName = :subjectName and s.accepted = true";
        Transaction tx = null;
        List<SubjectModeratorRelationship> smrList = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("accepted", subjectName);
            smrList = (List<SubjectModeratorRelationship>) query.getResultList();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return smrList;
    }
}
