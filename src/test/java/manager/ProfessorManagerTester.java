package manager;

import entity.Professor;
import entity.Subject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Created by tomi on 02/04/17.
 */
public class ProfessorManagerTester {

    private Subject persistAndGiveSubject(Professor professor){
        SubjectManager subjectManager = new SubjectManager();
        Integer subjectID = subjectManager.addToDatabase(new Subject("Ingles", professor));
        return subjectManager.get(Subject.class, subjectID);
    }

    private Professor persistAndGiveProfessor(){
        ProfessorManager professorManager = new ProfessorManager();
        Integer professorID = professorManager.addToDatabase(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorManager.get(Professor.class, professorID);
    }


    @Test
    public void addSubjectTest(){

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        ProfessorManager professorManager = new ProfessorManager();
        professorManager.addSubject(professor, subject);

        List<Professor> list = professorManager.listProfessors();

        assertThat(list.get(0).getSubjects().get(0).getSubjectName()).isEqualTo(subject.getSubjectName());
    }
}
