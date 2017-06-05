package org.australteca.dao;

import org.australteca.entity.Note;

import java.util.List;

/**
 * Created by tomi on 19/04/17.
 */
public class NoteDao extends AbstractDao<Note> {

    @Override
    public void delete(Integer id) throws IllegalArgumentException {
        super.delete(Note.class, id);
    }

    @Override
    public Note get(Integer id) {
        return super.get(Note.class, id);
    }

    @Override
    public List<Note> list() {
        return super.list(Note.class);
    }
}
