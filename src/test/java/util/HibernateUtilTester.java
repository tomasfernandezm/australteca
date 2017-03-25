package util;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by tomi on 25/03/17.
 */
public class HibernateUtilTester {

    @Test
    public void hibernateUtilTest(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        assertTrue(sessionFactory.isOpen());
    }
}
