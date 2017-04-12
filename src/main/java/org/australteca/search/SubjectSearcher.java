package org.australteca.search;

import org.australteca.entity.Subject;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.australteca.util.HibernateUtil;

import java.util.List;

/**
 * Created by tomi on 07/04/17.
 */
public class SubjectSearcher {

    public List searchSubject(String search){

        Session session = HibernateUtil.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        // create native Lucene query using the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Subject.class).get();

        Query query = queryBuilder.keyword().onField("subjectName").matching(search).createQuery();

        // wrap Lucene query in a org.hibernate.Query
        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Subject.class);

        // execute org.australteca.search
        List result = hibQuery.list();

        tx.commit();

        return result;
    }
}
