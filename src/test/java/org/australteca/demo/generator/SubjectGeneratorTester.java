package org.australteca.demo.generator;

import org.australteca.dao.SubjectDao;
import org.australteca.demo.constants.SubjectConstants;
import org.australteca.demo.generator.SubjectGenerator;
import org.australteca.entity.Subject;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tomi on 12/07/17.
 */
public class SubjectGeneratorTester {

    @Test
    public void generationTest(){
        SubjectGenerator generator = new SubjectGenerator();
        generator.generate();

        SubjectDao subjectDao = new SubjectDao();

        List<Subject> subjectList = subjectDao.list();
        String[] subjectNames = SubjectConstants.carrerasIngenieríaInformática;

        assertThat(subjectList).hasSize(subjectNames.length);
    }
}
