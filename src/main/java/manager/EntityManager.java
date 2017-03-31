package manager;

import com.sun.istack.internal.NotNull;
import entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;

/**
 * Created by tomi on 29/03/17.
 */
public class EntityManager<T> {

    protected Integer addToDatabase(T t){

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

    T get(Class<T> genericClass, Integer id){
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

    protected <R> void modify(Class<T> genericClass, @NotNull Integer entityID, @NotNull R toModify,
                          @NotNull BiConsumer<T, R> biConsumer){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            T t = (T) session.get(genericClass, entityID);
            biConsumer.accept(t, toModify);
            session.update(t);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    protected List<T> listEntities(Class<T> genericClass){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        List<T> t = new ArrayList<T>();
        try{
            tx = session.beginTransaction();
            // t = session.createQuery("FROM " + genericClass.getSimpleName()).list();
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
