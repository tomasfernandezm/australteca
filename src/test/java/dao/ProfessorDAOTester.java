package dao;

import entity.Professor;
import entity.Subject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Created by tomi on 02/04/17.
 */
public class ProfessorDAOTester {

    private Subject persistAndGiveSubject(Professor professor){
        SubjectDAO subjectDAO = new SubjectDAO();
        Integer subjectID = subjectDAO.addToDatabase(new Subject("Ingles"));
        return subjectDAO.get(Subject.class, subjectID);
    }

    private Professor persistAndGiveProfessor(){
        ProfessorDAO professorDAO = new ProfessorDAO();
        Integer professorID = professorDAO.addToDatabase(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorDAO.get(Professor.class, professorID);
    }


    @Test
    public void addSubjectTest(){

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        ProfessorDAO professorDAO = new ProfessorDAO();
        professor.addSubject(subject);

        List<Professor> list = professorDAO.listProfessors();

        assertThat(list.get(0).getSubjects().get(0).getSubjectName()).isEqualTo(subject.getSubjectName());
    }
}
