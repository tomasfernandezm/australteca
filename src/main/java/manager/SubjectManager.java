package manager;

import com.sun.istack.internal.NotNull;
import entity.Professor;
import entity.Subject;

import javax.persistence.EntityNotFoundException;
import java.util.function.BiConsumer;

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

    public void addProfessor(@NotNull Integer subjectID, @NotNull Professor professor){
        BiConsumer<Subject, Professor> biConsumer = (subject, professorToAdd) ->{
            subject.getProfessors().add(professorToAdd);
        };
        modify(Subject.class, subjectID, professor, biConsumer);
    }
}
