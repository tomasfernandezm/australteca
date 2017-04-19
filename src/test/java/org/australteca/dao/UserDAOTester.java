package org.australteca.dao;

import org.australteca.entity.Commentary;
import org.australteca.entity.Professor;
import org.australteca.entity.Subject;
import org.australteca.entity.User;
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
        SubjectDao subjectDAO = new SubjectDao();
        Integer subjectID = subjectDAO.add(new Subject("Ingles"));
        return subjectDAO.get(Subject.class, subjectID);
    }

    private Professor persistAndGiveProfessor(){
        ProfessorDao professorDAO = new ProfessorDao();
        Integer professorID = professorDAO.add(new Professor("Pepito",
                "Gimenez", "licenciado"));
        return professorDAO.get(Professor.class, professorID);
    }

    @Test
    public void addCommentaryTest(){
        UserDao userDAO = new UserDao();

        Integer userID = userDAO.add(giveUser());
        User user = userDAO.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getCommentaries().add(new Commentary("aaaaa", user,subject));
        user.getCommentaries().add(new Commentary("bbbbb", user,subject));
        user.getCommentaries().add(new Commentary("ccccc", user,subject));

        userDAO.merge(user);
        CommentaryDao commentaryDAO = new CommentaryDao();
        List<Commentary> list = commentaryDAO.list();

        for(Commentary c: user.getCommentaries()){
            assertThat(list).contains(c);
        }
    }

    @Test
    public void deleteAllCommentariesOnUserdelete(){
        UserDao userDAO = new UserDao();

        Integer userID = userDAO.add(giveUser());
        User user = userDAO.get(User.class, userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getCommentaries().add(new Commentary("aaaaa", user,subject));
        user.getCommentaries().add(new Commentary("bbbbb", user,subject));
        user.getCommentaries().add(new Commentary("ccccc", user,subject));

        userDAO.merge(user);

        CommentaryDao commentaryDAO = new CommentaryDao();
        userDAO.delete(user.getId());

        List<Commentary> list = commentaryDAO.list();
        assertThat(list).hasSize(0);
    }

    @Test
    public void addSubjectTest(){
        UserDao userDAO = new UserDao();

        Integer userID = userDAO.add(giveUser());
        User user = userDAO.get(userID);

        Professor professor = persistAndGiveProfessor();
        Subject subject = persistAndGiveSubject(professor);

        user.getSubjects().add(subject);
        userDAO.merge(user);
        List<Subject> list = (new SubjectDao()).list();
        assertThat(list).hasSize(1);
    }

    @Test
    public void removeCommentaryTest(){

    }

    @Test
    public void removeSubjectTest(){

    }
}
