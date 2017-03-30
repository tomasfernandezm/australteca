package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "SUBJECT")
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "SUBJECT_ID")
    private int id;

    @OneToMany
    private final List<Commentary> commentaryList = new ArrayList<Commentary>();

    @Column (name = "SUBJECT_SCORE")
    private double score;

    @Column (name = "SUBJECT_AMOUNT_OF_SCORES")
    private double amountOfScores;

    @Column (name = "SUBJECT_NAME")
    private String subjectName;

    @ManyToMany
    private final List<Professor> professors = new ArrayList<Professor>();

    @OneToMany
    private final List<Commentary> commentaries = new ArrayList<Commentary>();

    @ManyToMany
    private final List<User> subscribedUsers = new ArrayList<User>();

    public Subject() {
    }

    public Subject(String subjectName, Professor professor) {
        this.subjectName = subjectName;
        professors.add(professor);
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<Commentary> getCommentaryList() {
        return commentaryList;
    }

    public double getScore() {
        return score;
    }

    public double getAmountOfScores() {
        return amountOfScores;
    }

    public void addToScore(double scoreAdd){
        score = amountOfScores*score + scoreAdd;
        amountOfScores++;
        score = score/amountOfScores;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<User> getSubscribedUsers() {
        return subscribedUsers;
    }
}
