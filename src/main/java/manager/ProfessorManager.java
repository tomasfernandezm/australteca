package manager;

import com.sun.istack.internal.NotNull;
import entity.Professor;
import entity.Subject;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class ProfessorManager extends AbstractManager<Professor> {

    public void delete(@NotNull Integer professorID){
        deleteFromDatabase(Professor.class, professorID);
    }

    public void addSubject(@NotNull Professor professor, @NotNull Subject subject){
        professor.getSubjects().add(subject);
        update(professor);
    }

    public void deleteSubject(@NotNull Professor professor, @NotNull Subject subject){
        professor.getSubjects().remove(subject);
        update(professor);
    }

    public void modifyDegree(@NotNull Professor professor, @NotNull String degree){
        professor.setDegree(degree);
        update(professor);
    }

    public List<Professor> listProfessors() {
        return listEntities(Professor.class);
    }

    public Professor getProfessor(@NotNull Integer professorID){
        return get(Professor.class, professorID);
    }
}
