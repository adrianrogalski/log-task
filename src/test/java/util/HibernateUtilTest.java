package util;

import org.hibernate.query.NativeQuery;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateUtilTest {
    @Test
    void testConnection() {
        // given
        final var sessionFactory = HibernateUtil.getSessionFactory();
        final var session = sessionFactory.openSession();
        // when
        final NativeQuery query = session.createSQLQuery("SELECT 1");
        final List resultList = query.getResultList();
        // then
        assertEquals(1,resultList.get(0));

    }
}