package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.GenericEntity;

import java.util.List;

/**
 * Created by tomi on 07/04/17.
 */
public class GenericEntityDao extends AbstractDao<GenericEntity> {

    public void delete(@NotNull Integer professorID){
        delete(GenericEntity.class, professorID);
    }

    public List<GenericEntity> list() {
        return list(GenericEntity.class);
    }

    public GenericEntity get(@NotNull Integer professorID){
        return get(GenericEntity.class, professorID);
    }
}
