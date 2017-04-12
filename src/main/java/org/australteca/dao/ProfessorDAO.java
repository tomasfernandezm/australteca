package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Professor;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class ProfessorDAO extends AbstractDAO<Professor> {

    public void delete(@NotNull Integer professorID){
        deleteFromDatabase(Professor.class, professorID);
    }

    public List<Professor> listProfessors() {
        return listEntities(Professor.class);
    }

    public Professor getProfessor(@NotNull Integer professorID){
        return get(Professor.class, professorID);
    }
}
