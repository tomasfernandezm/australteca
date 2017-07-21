package org.australteca.demo.generator;

import org.australteca.dao.ProfessorDao;
import org.australteca.dao.SubjectDao;
import org.australteca.demo.constants.ProfessorConstants;
import org.australteca.demo.constants.UserConstants;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.australteca.entity.SubjectModeratorRelationship;
import org.australteca.entity.User;

import java.util.List;

/**
 * Created by tomi on 14/07/17.
 */
public class ProfessorGenerator extends AbstractGenerator{

    private ProfessorDao professorDao = new ProfessorDao();
    private int amount = 100;

    public ProfessorGenerator(){

    }

    public ProfessorGenerator(int amount){
        this.amount = amount;
    }

    @Override
    public void generate() {

        List<Subject> subjects = new SubjectDao().list();
        String[] names = UserConstants.names;
        String[] lastNames = UserConstants.lastNames;
        String[] information = ProfessorConstants.information;

        for(int i = 0;i< amount;i++){
            double r = random.nextDouble();
            String name = names[getIndex(0, names.length, r)];
            String ln = lastNames[getIndex(0, lastNames.length, r)];
            String info = information[getIndex(0, information.length, r)];
            Professor p = new Professor(name, ln, makeEmail(name, ln), info);
            Integer id = professorDao.add(p);
            p = professorDao.get(id);
            Subject s = subjects.get(getIndex(0, subjects.size(), r));
            s.getProfessors().add(p);
            p.getSubjects().add(s);
        }
    }

    String makeEmail(String name, String lastName){

        String fn = name.toLowerCase();
        String ln = lastName.toLowerCase();

        return fn + "." + ln + "@ing.austral.edu.ar";
    }
}
