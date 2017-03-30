package manager;

import com.sun.istack.internal.NotNull;
import entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import util.HibernateUtil;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.junit.Assert.*;

/**
 * Created by tomi on 29/03/17.
 */
public class EntityManagerTester {

    private Entity giveEntity(){
        return new Entity(7, "Pepito", "Rodriguez", 16);
    }

    private Entity giveOtherEntity(){
        return new Entity(8, "Mengano", "Sotano", 20);
    }

    private Entity getEntity(@NotNull Integer entityID){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx =  null;
        Entity entity = null;
        try{
            tx = session.beginTransaction();
            entity = session.get(Entity.class, entityID);
        }catch (HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return entity;
    }

    @Test
    public void addToDatabaseTest(){
        EntityManager<Entity> entityManager = new EntityManager<Entity>();
        entityManager.addToDatabase(giveEntity());

        Entity entity = getEntity(7);
        assertThat(entity).isNotNull();

        Entity nullEntity = getEntity(8);
        assertThat(nullEntity).isNull();
    }

    @Test
    public void deleteFromDatabaseTest(){
        EntityManager<Entity> entityManager = new EntityManager<Entity>();
        entityManager.addToDatabase(giveEntity());
        entityManager.deleteFromDatabase(Entity.class, 7);
        try {
            entityManager.deleteFromDatabase(Entity.class, 8);
        }catch (Exception e){
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        Entity entity = getEntity(7);
        assertThat(entity).isNull();
    }

    @Test
    public void listEntitiesTest(){
        EntityManager<Entity> entityManager = new EntityManager<Entity>();
        entityManager.addToDatabase(giveEntity());
        entityManager.addToDatabase(giveOtherEntity());

        List<Entity> entityList = entityManager.listEntities(Entity.class);

        assertThat(entityList).hasSize(2);
        assertThat(entityList.get(0).equals(giveEntity())).isTrue();
        assertThat(entityList.get(1).equals(giveOtherEntity())).isTrue();
    }
}
