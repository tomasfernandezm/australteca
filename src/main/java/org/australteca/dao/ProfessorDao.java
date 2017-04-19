package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Professor;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class ProfessorDao extends AbstractDao<Professor> {

    public void delete(@NotNull Integer professorID){
        delete(Professor.class, professorID);
    }

    public List<Professor> list() {
        return list(Professor.class);
    }

    public Professor get(@NotNull Integer professorID){
        return get(Professor.class, professorID);
    }
}
