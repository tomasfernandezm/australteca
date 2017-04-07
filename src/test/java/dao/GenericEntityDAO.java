package dao;

import com.sun.istack.internal.NotNull;
import entity.Professor;

import java.util.List;

/**
 * Created by tomi on 07/04/17.
 */
public class GenericEntityDAO extends AbstractDAO<GenericEntity>{

    public void delete(@NotNull Integer professorID){
        deleteFromDatabase(GenericEntity.class, professorID);
    }

    public List<GenericEntity> listGenericEntities() {
        return listEntities(GenericEntity.class);
    }

    public GenericEntity getGenericEntity(@NotNull Integer professorID){
        return get(GenericEntity.class, professorID);
    }
}
