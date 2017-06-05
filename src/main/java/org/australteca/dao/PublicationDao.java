package org.australteca.dao;

import org.australteca.entity.Professor;
import org.australteca.entity.Publication;

import java.util.List;

/**
 * Created by tomi on 27/05/17.
 */
public class PublicationDao extends AbstractDao<Publication> {

    @Override
    public void delete(Integer id) throws IllegalArgumentException {
        super.delete(Publication.class, id);
    }

    @Override
    public Publication get(Integer id) {
        return super.get(Publication.class, id);
    }

    @Override
    public List<Publication> list() {
        return super.list(Publication.class);
    }
}
