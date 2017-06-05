package org.australteca.dao;

import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

/**
 * Created by tomi on 02/04/17.
 */
public class ProfessorDAOTester {

    /*private Subject persistAndGiveSubject(Professor professor){
        SubjectDao subjectDAO = new SubjectDao();
        Integer subjectID = subjectDAO.add(new Subject("Ingles"));
        return subjectDAO.get(Subject.class, subjectID);
    }

    *//*private Professor persistAndGiveProfessor(){
        ProfessorDao professorDAO = new ProfessorDao();
        Integer professorID = professorDAO.add(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorDAO.get(Professor.class, professorID);
    }*//*


    @Test
    public void addSubjectTest(){

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        ProfessorDao professorDAO = new ProfessorDao();
        professor.addSubject(subject);

        List<Professor> list = professorDAO.list();

        assertThat(list.get(0).getSubjects().get(0).getSubjectName()).isEqualTo(subject.getSubjectName());
    }*/
}
