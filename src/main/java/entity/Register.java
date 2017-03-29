package entity;

import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


/**
 * Created by tomasforman on 28/3/17.
 */
public class Register {
    private static SessionFactory factory;






    public static int saveUser(String name, String lName, String email, String password, String passwordC, String career){
        int status = -1;
        if(name != null && lName!=null && checkEmail(email) && checkPassword(password, passwordC)) {
            User userRegister = new User("6", name, lName, email, career, password, "user");
            addDB(userRegister);
            status = 1;
        }
        return status;
    }

    private static void addDB(User user){
        try{
            factory = new org.hibernate.cfg.Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    private static Boolean checkPassword(String p1, String p2){
        if(p1 == null || p2 == null)return false;
        if(p1.equals(p2))return true;
        return false;
    }


    private static Boolean checkEmail(String email){
        return true;
    }
}
