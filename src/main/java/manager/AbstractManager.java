package manager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public abstract class AbstractManager<T> {

    Integer addToDatabase(T t){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        Integer result = null;
        try{
            tx = session.beginTransaction();
            result = (Integer) session.save(t);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    void deleteFromDatabase(Class<T> genericClass, Integer id) throws IllegalArgumentException{

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            T t = (T) session.get(genericClass, id);
            session.delete(t); // if null throws IllegalArgumentException
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    protected T get(Class<T> genericClass, Integer id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        T result = null;
        try{
            tx = session.beginTransaction();
            result = (T) session.get(genericClass, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    <R> void update(T toUpdate){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(toUpdate);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    List<T> listEntities(Class<T> genericClass){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<T> t = new ArrayList<T>();
        try{
            tx = session.beginTransaction();
            t = session.createQuery("FROM " + genericClass.getSimpleName(), genericClass).list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return t;
    }
}
