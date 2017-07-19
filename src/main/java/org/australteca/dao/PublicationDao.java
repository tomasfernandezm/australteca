package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.Constants;
import org.australteca.entity.Publication;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
import org.australteca.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 27/05/17.
 */
public class PublicationDao extends AbstractDao<Publication> {

    @Override
    public void delete(Integer id) throws IllegalArgumentException {
        Publication publication = get(id);
        removeAllUsers(publication);
        User u = publication.getAuthor();
        u.getPublications().remove(publication);
        publication.getSuscribedUsers().clear();
        merge(publication);
        delete(Publication.class, id);
    }

    @Override
    public Publication get(Integer id) {
        return super.get(Publication.class, id);
    }

    @Override
    public List<Publication> list() {
        return super.list(Publication.class);
    }

    private void removeAllUsers(@NotNull Publication publication){
        if(!publication.getSuscribedUsers().isEmpty()){
            List<User> users = publication.getSuscribedUsers();
            for(User u: users) {
                u.getPublications().remove(publication);
            }
        }
    }

    public List<Publication> list(String roleType){
        Session session = HibernateUtil.getCurrentSession();
        String hql = "from Publication p where p.role = :role";
        Transaction tx = null;
        List<Publication> publications = null;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery(hql).setParameter("role", roleType);
            publications = query.getResultList();
            tx.commit();
        }catch (HibernateException | NoResultException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        return publications;
    }

    public List<Publication> search(String title){
        Session session = HibernateUtil.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();

        // create native Lucene query using the query DSL
        // alternatively you can write the Lucene query using the Lucene query parser
        // or the Lucene programmatic API. The Hibernate Search DSL is recommended though
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Publication.class).get();

       org.apache.lucene.search.Query query = queryBuilder.keyword().onField("name").matching(title).createQuery();

        // wrap Lucene query in a org.hibernate.Query
        org.hibernate.query.Query hibQuery =
                fullTextSession.createFullTextQuery(query, Publication.class);

        // execute search
        List<Publication> result = (List<Publication>) hibQuery.list();

        tx.commit();

        return result;
    }
}
