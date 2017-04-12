package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Subject;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class SubjectDAO extends AbstractDAO<Subject> {

    public void delete(@NotNull Integer subjectID){
        deleteFromDatabase(Subject.class, subjectID);
    }

    public List<Subject> listSubjects() {
        return listEntities(Subject.class);
    }

    public Subject get(@NotNull final Integer id) {
        return get(Subject.class, id);
    }
}
