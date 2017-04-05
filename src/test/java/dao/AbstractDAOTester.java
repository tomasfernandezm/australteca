package dao;

import com.sun.istack.internal.NotNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import util.HibernateUtil;

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

    private GenericEntity getEntity(@NotNull Integer entityID){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx =  null;
        GenericEntity genericEntity = null;
        try{
            tx = session.beginTransaction();
            genericEntity = session.get(GenericEntity.class, entityID);
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {

        }
        return genericEntity;
    }

    @Test
    public void addToDatabaseTest(){
        EntityDAO entityManager = new EntityDAO();
        Integer entityID = entityManager.addToDatabase(giveEntity());

        GenericEntity genericEntity = getEntity(entityID);
        assertThat(genericEntity).isNotNull();

        GenericEntity nullGenericEntity = getEntity(entityID + 1);
        assertThat(nullGenericEntity).isNull();
    }

    @Test
    public void deleteFromDatabaseTest(){
        EntityDAO abstractManager = new EntityDAO();
        Integer entityID = abstractManager.addToDatabase(giveEntity());
        abstractManager.deleteFromDatabase(GenericEntity.class, entityID);
        try {
            abstractManager.deleteFromDatabase(GenericEntity.class, entityID + 1);
        }catch (Exception e){
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        GenericEntity genericEntity = getEntity(entityID);
        assertThat(genericEntity).isNull();
    }

    @Test
    public void listEntitiesTest(){
        EntityDAO abstractManager = new EntityDAO();
        abstractManager.addToDatabase(giveEntity());
        abstractManager.addToDatabase(giveOtherEntity());

        List<GenericEntity> genericEntityList = abstractManager.listEntities(GenericEntity.class);

        assertThat(genericEntityList).hasSize(2);
        assertThat(genericEntityList.get(0).getFirstName().equals("Pepito")).isTrue();
        assertThat(genericEntityList.get(1).getFirstName().equals("Mengano")).isTrue();
    }

    private class EntityDAO extends AbstractDAO<GenericEntity> {

    }
}