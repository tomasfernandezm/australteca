package org.australteca.demo;

import org.australteca.dao.SubjectDao;
import org.australteca.demo.constants.SubjectConstants;
import org.australteca.entity.Subject;

/**
 * Created by tomi on 27/06/17.
 */
public class SubjectGenerator implements Generator{

    @Override
    public void generate() {
        SubjectDao subjectDao = new SubjectDao();
        String[] subjectNames = SubjectConstants.carrerasIngenieríaInformática;
        for(String s: subjectNames){
            subjectDao.add(new Subject(s));
        }
    }
}
