package dao;

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
public class UserDAOTester {

    private User giveUser(){
        return new User("Pepito", "Rodriguez", "p.rodriguez@ing.austral.edu.ar",
                "Informática", "123456789", false, false);
    }

    private Commentary giveCommentary(User author, Subject subject){
        return new Commentary("aaaaa", author, subject);
    }

    private Subject persistAndGiveSubject(Professor professor){
        SubjectDAO subjectDAO = new SubjectDAO();
        Integer subjectID = subjectDAO.addToDatabase(new Subject("Ingles"));
        return subjectDAO.get(Subject.class, subjectID);
    }

    private Professor persistAndGiveProfessor(){
        ProfessorDAO professorDAO = new ProfessorDAO();
        Integer professorID = professorDAO.addToDatabase(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorDAO.get(Professor.class, professorID);
    }

    @Test
    public void addCommentaryTest(){
        UserDAO userDAO = new UserDAO();

        Integer userID = userDAO.addToDatabase(giveUser());
        User user = userDAO.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getCommentaries().add(new Commentary("aaaaa", user,subject));
        user.getCommentaries().add(new Commentary("bbbbb", user,subject));
        user.getCommentaries().add(new Commentary("ccccc", user,subject));

        userDAO.update(user);
        CommentaryDAO commentaryDAO = new CommentaryDAO();
        List<Commentary> list = commentaryDAO.listCommentaries();

        for(Commentary c: user.getCommentaries()){
            assertThat(list).contains(c);
        }
    }

    @Test
    public void deleteAllCommentariesOnUserdelete(){
        UserDAO userDAO = new UserDAO();

        Integer userID = userDAO.addToDatabase(giveUser());
        User user = userDAO.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getCommentaries().add(new Commentary("aaaaa", user,subject));
        user.getCommentaries().add(new Commentary("bbbbb", user,subject));
        user.getCommentaries().add(new Commentary("ccccc", user,subject));

        userDAO.update(user);

        CommentaryDAO commentaryDAO = new CommentaryDAO();
        userDAO.delete(user.getId());

        List<Commentary> list = commentaryDAO.listCommentaries();
        assertThat(list).hasSize(0);
    }

    @Test
    public void addSubjectTest(){
        UserDAO userDAO = new UserDAO();

        Integer userID = userDAO.addToDatabase(giveUser());
        User user = userDAO.getUser(userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getSubjects().add(subject);
        userDAO.update(user);
        List<Subject> list = (new SubjectDAO()).listSubjects();
        assertThat(list).hasSize(1);
    }

    @Test
    public void removeCommentaryTest(){

    }

    @Test
    public void removeSubjectTest(){

    }
}
