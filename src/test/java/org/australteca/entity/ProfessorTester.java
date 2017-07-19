package org.australteca.entity;

import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectDao;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 19/07/17.
 */
public class ProfessorTester {

    @Test
    public void addWithLongInformation(){

        Subject subject = new Subject("Prog");
        Professor professor = new Professor("A", "B", "a.b@ing.austral.edu.ar", getInformation());

        SubjectDao subjectDao = new SubjectDao();
        ProfessorDao professorDao = new ProfessorDao();

        Integer subjectID = subjectDao.add(subject);
        Integer professorID = professorDao.add(professor);

        assertThat(subjectID).isNotNull();
        assertThat(professorID).isNotNull();
        assertThat(getInformation().length()).isLessThan(1024*1024);
    }

    private String getInformation() {
        return "Juan Domingo Perón (Lobos, 8 de octubre de 1895-Vicente López, 1 de julio de 1974) fue un" +
                "militar, político, escritor y presidente argentino, el primero en ser elegido por " +
                "sufragio universal y el único hasta la fecha en asumir la presidencia de la Nación en " +
                "tres ocasiones, todas por medio de elecciones democráticas. Fue el fundador del " +
                "peronismo, uno de los movimientos populares más importantes de la historia de " +
                "la Argentina hasta la actualidad.";
    }
}
