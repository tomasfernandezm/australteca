package org.australteca.util;

import org.australteca.utils.HibernateUtil;
import org.hibernate.SessionFactory;
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
