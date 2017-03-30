package manager;

import com.sun.istack.internal.NotNull;
import entity.Professor;
import entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.persistence.EntityNotFoundException;

/**
 * Created by tomi on 29/03/17.
 */
public class ProfessorManager extends EntityManager<Professor> {

    public void add(@NotNull String firstName, @NotNull String lastName, @NotNull String degree){
        addToDatabase(new Professor(firstName, lastName, degree));
    }

    public void delete(@NotNull Integer professorID){
        deleteFromDatabase(Professor.class, professorID);
    }

    public void addSubject(@NotNull Integer professorID, @NotNull Subject subject){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Professor professor = (Professor) session.get(Professor.class, professorID);
            professor.addSubject(subject);
            session.update(professor);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
