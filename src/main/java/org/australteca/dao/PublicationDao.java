package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Professor;
import org.australteca.entity.Publication;
import org.australteca.entity.Subject;
import org.australteca.entity.User;

import java.util.List;

/**
 * Created by tomi on 27/05/17.
 */
public class PublicationDao extends AbstractDao<Publication> {

    @Override
    public void delete(Integer id) throws IllegalArgumentException {
        Publication publication = get(id);
        removeAllUsers(publication);
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
            for(User u: publication.getSuscribedUsers()) {
                u.getFavoritePublications().remove(publication);
            }
        }
    }
}
