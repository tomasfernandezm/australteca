package org.australteca.dao;

import org.australteca.entity.GenericEntity;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomi on 29/03/17.
 */
public class AbstractDaoTester {

    private GenericEntity giveEntity(){
        return new GenericEntity("Pepito", "Rodriguez", 16);
    }

    private GenericEntity giveOtherEntity(){
        return new GenericEntity("Mengano", "Sotano", 20);
    }

    @Test
    public void addToDatabaseTest(){
        GenericEntityDao entityManager = new GenericEntityDao();
        Integer entityID = entityManager.add(giveEntity());

        GenericEntity genericEntity = entityManager.get(GenericEntity.class, entityID);
        assertThat(genericEntity).isNotNull();

        GenericEntity nullGenericEntity = entityManager.get(GenericEntity.class, entityID + 1);
        assertThat(nullGenericEntity).isNull();
    }

    @Test
    public void deleteFromDatabaseTest(){
        GenericEntityDao abstractManager = new GenericEntityDao();
        Integer entityID = abstractManager.add(giveEntity());
        abstractManager.delete(GenericEntity.class, entityID);
        try {
            abstractManager.delete(GenericEntity.class, entityID + 1);
        }catch (Exception e){
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        GenericEntity genericEntity = abstractManager.get(entityID);
        assertThat(genericEntity).isNull();
    }

    @Test
    public void listEntitiesTest() {
        GenericEntityDao abstractManager = new GenericEntityDao();
        abstractManager.add(giveEntity());
        abstractManager.add(giveOtherEntity());

        List<GenericEntity> genericEntityList = abstractManager.list(GenericEntity.class);

        assertThat(genericEntityList).hasSize(2);
        assertThat(genericEntityList.get(0).getFirstName().equals("Pepito")).isTrue();
        assertThat(genericEntityList.get(1).getFirstName().equals("Mengano")).isTrue();
    }
}
