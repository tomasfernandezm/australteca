package org.australteca.dao;

import org.australteca.entity.EntityInterface;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.australteca.utils.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public abstract class AbstractDao<T extends EntityInterface> implements Dao<T>{

    public Integer add(T t){

        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = null;
        Integer result = null;
        try{
            tx = session.beginTransaction();
            result = (Integer) session.save(t);
            tx.commit();
        }catch (PersistenceException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public void delete(Class<T> genericClass, Integer id) throws IllegalArgumentException{

        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            T t = session.get(genericClass, id);
            session.delete(t); // if null throws IllegalArgumentException
            tx.commit();
        }catch (HibernateException | IllegalArgumentException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public T get(Class<T> genericClass, Integer id){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = null;
        T result = null;
        try{
            tx = session.beginTransaction();
            result = session.get(genericClass, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return result;
    }

    public void merge(T toUpdate){

        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.merge(toUpdate);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<T> list(Class<T> genericClass){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx = null;
        List<T> t = new ArrayList<T>();
        try{
            tx = session.beginTransaction();
            t = session.createQuery("FROM " + genericClass.getSimpleName(), genericClass).list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return t;
    }
}
