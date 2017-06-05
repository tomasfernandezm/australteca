package org.australteca.dao;

import com.sun.istack.internal.NotNull;
import org.australteca.entity.Commentary;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class CommentaryDao extends AbstractDao<Commentary> {

    public void delete(@NotNull Integer commentaryID){
        super.delete(Commentary.class, commentaryID);
    }

    public Commentary get(@NotNull Integer id){
        return super.get(Commentary.class, id);
    }

    public List<Commentary> list() {
        return list(Commentary.class);
    }
}
