package org.australteca.dao;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomi on 29/03/17.
 */
public class AbstractDAOTester {

    private GenericEntity giveEntity(){
        return new GenericEntity("Pepito", "Rodriguez", 16);
    }

    private GenericEntity giveOtherEntity(){
        return new GenericEntity("Mengano", "Sotano", 20);
    }

    @Test
    public void addToDatabaseTest(){
        GenericEntityDAO entityManager = new GenericEntityDAO();
        Integer entityID = entityManager.addToDatabase(giveEntity());

        GenericEntity genericEntity = entityManager.get(GenericEntity.class, entityID);
        assertThat(genericEntity).isNotNull();

        GenericEntity nullGenericEntity = entityManager.get(GenericEntity.class, entityID + 1);
        assertThat(nullGenericEntity).isNull();
    }

    @Test
    public void deleteFromDatabaseTest(){
        GenericEntityDAO abstractManager = new GenericEntityDAO();
        Integer entityID = abstractManager.addToDatabase(giveEntity());
        abstractManager.deleteFromDatabase(GenericEntity.class, entityID);
        try {
            abstractManager.deleteFromDatabase(GenericEntity.class, entityID + 1);
        }catch (Exception e){
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        GenericEntity genericEntity = abstractManager.getGenericEntity(entityID);
        assertThat(genericEntity).isNull();
    }

    @Test
    public void listEntitiesTest() {
        GenericEntityDAO abstractManager = new GenericEntityDAO();
        abstractManager.addToDatabase(giveEntity());
        abstractManager.addToDatabase(giveOtherEntity());

        List<GenericEntity> genericEntityList = abstractManager.listEntities(GenericEntity.class);

        assertThat(genericEntityList).hasSize(2);
        assertThat(genericEntityList.get(0).getFirstName().equals("Pepito")).isTrue();
        assertThat(genericEntityList.get(1).getFirstName().equals("Mengano")).isTrue();
    }
}
