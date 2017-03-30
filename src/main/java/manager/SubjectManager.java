package manager;

import com.sun.istack.internal.NotNull;
import entity.Professor;
import entity.Subject;

import javax.persistence.EntityNotFoundException;

/**
 * Created by tomi on 29/03/17.
 */
public class SubjectManager extends EntityManager<Subject> {

    public void add(@NotNull String name, @NotNull Professor professor){
        addToDatabase(new Subject(name, professor));
    }

    public void delete(@NotNull Integer subjectID){
        deleteFromDatabase(Subject.class, subjectID);
    }
}
