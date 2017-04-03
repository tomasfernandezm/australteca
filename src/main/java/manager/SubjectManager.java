package manager;

import com.sun.istack.internal.NotNull;
import entity.Commentary;
import entity.Professor;
import entity.Subject;
import entity.User;

import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */
public class SubjectManager extends AbstractManager<Subject> {

    public void delete(@NotNull Integer subjectID){
        deleteFromDatabase(Subject.class, subjectID);
    }

    public void addProfessor(@NotNull Subject subject, @NotNull Professor professor){
        subject.getProfessors().add(professor);
        update(subject);
    }

    public void removeProfessor(@NotNull Subject subject, @NotNull Professor professor){
        subject.getProfessors().remove(professor);
        update(subject);
    }

    public void addUser(@NotNull Subject subject, @NotNull User user){
        subject.getSubscribedUsers().add(user);
        update(subject);
    }

    public void removeUser(@NotNull Subject subject, @NotNull User user){
        subject.getSubscribedUsers().remove(user);
        update(subject);
    }

    public void addCommentary(@NotNull Subject subject, @NotNull Commentary commentary){
        subject.getCommentaryList().add(commentary);
        update(subject);
    }

    public void removeCommentary(@NotNull Subject subject, @NotNull Commentary commentary){
        subject.getCommentaryList().add(commentary);
        update(subject);
    }

    public List<Subject> listSubjects() {
        return listEntities(Subject.class);
    }

    public Subject get(@NotNull final Integer id) {
        return get(Subject.class, id);
    }
}
