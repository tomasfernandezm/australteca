package manager;

import entity.Commentary;
import entity.Professor;
import entity.Subject;
import entity.User;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by tomi on 02/04/17.
 */
public class UserManagerTester {

    private User giveUser(){
        return new User("Pepito", "Rodriguez", "p.rodriguez@ing.austral.edu.ar",
                "Informática", "123456789", false, false);
    }

    private Commentary giveCommentary(User author, Subject subject){
        return new Commentary("aaaaa", author, subject);
    }

    private Subject persistAndGiveSubject(Professor professor){
        SubjectManager subjectManager = new SubjectManager();
        Integer subjectID = subjectManager.addToDatabase(new Subject("Ingles", professor));
        return subjectManager.get(Subject.class, subjectID);
    }

    private Professor persistAndGiveProfessor(){
        ProfessorManager professorManager = new ProfessorManager();
        Integer professorID = professorManager.addToDatabase(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorManager.get(Professor.class, professorID);
    }

    @Test
    public void addCommentaryTest(){
        UserManager userManager = new UserManager();

        Integer userID = userManager.addToDatabase(giveUser());
        User user = userManager.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        userManager.addComentary(user, "aaaaa", subject);
        userManager.addComentary(user, "bbbbb", subject);
        userManager.addComentary(user, "ccccc", subject);

        CommentaryManager commentaryManager = new CommentaryManager();
        List<Commentary> list = commentaryManager.listCommentaries();

        assertThat(list.get(0).getCommentary()).contains("aaaaa");
        assertThat(list.get(1).getCommentary()).contains("bbbbb");
        assertThat(list.get(2).getCommentary()).contains("ccccc");
    }

    @Test
    public void deleteAllCommentariesOnUserdelete(){
        UserManager userManager = new UserManager();

        Integer userID = userManager.addToDatabase(giveUser());
        User user = userManager.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        userManager.addComentary(user, "aaaaa", subject);
        userManager.addComentary(user, "bbbbb", subject);
        userManager.addComentary(user, "ccccc", subject);

        CommentaryManager commentaryManager = new CommentaryManager();
        userManager.delete(user.getId());

        List<Commentary> list = commentaryManager.listCommentaries();
        assertThat(list).hasSize(0);
    }

    @Test
    public void addSubjectTest(){
        UserManager userManager = new UserManager();

        Integer userID = userManager.addToDatabase(giveUser());
        User user = userManager.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        userManager.addSubject(user, subject);
        List<Subject> list = (new SubjectManager()).listSubjects();
        assertThat(list).hasSize(1);
    }

    @Test
    public void removeCommentaryTest(){

    }

    @Test
    public void removeSubjectTest(){

    }
}
