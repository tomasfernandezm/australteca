package org.australteca.search;

import org.australteca.dao.SubjectDAO;
import org.australteca.entity.Subject;
import org.junit.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 07/04/17.
 */
public class SubjectSearcherTester {

    private void persistSubjects(){
        SubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.addToDatabase(new Subject("Analisis Matematico 1"));
        subjectDAO.addToDatabase(new Subject("Analisis Matematico 2"));
        subjectDAO.addToDatabase(new Subject("Analisis Matematico 3"));
        subjectDAO.addToDatabase(new Subject("Analisis Matematico 4"));
        subjectDAO.addToDatabase(new Subject("Algebra 3"));
        subjectDAO.addToDatabase(new Subject("Algoritmos y Bases "));
        subjectDAO.addToDatabase(new Subject("Algoritmos y Estructuras"));
        subjectDAO.addToDatabase(new Subject("Algas y microorganismos"));
        subjectDAO.addToDatabase(new Subject("Bases"));
    }


    @Test
    public void searchSubjectTest(){

        SubjectSearcher subjectSearcher = new SubjectSearcher();
        persistSubjects();
        List<Subject> subjectList = subjectSearcher.searchSubject("Analisis");
        for(Subject s: subjectList){
            assertThat(s.getSubjectName()).contains("Analisis");
        }

        subjectList = subjectSearcher.searchSubject("Bases");
        for(Subject s: subjectList){
            assertThat(s.getSubjectName()).contains("Bases");
        }

        subjectList = subjectSearcher.searchSubject("Datos");
        assertThat(subjectList).hasSize(0);

        subjectList = subjectSearcher.searchSubject("organismos");
        assertThat(subjectList).hasSize(0);
    }
}
