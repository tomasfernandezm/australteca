package org.australteca.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
public class Subject extends AbstractEntity{

    private double score;
    private double amountOfScores;

    @Column (unique = true)
    private String subjectName;

    @ManyToMany
    private final List<Professor> professors = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    private final List<Commentary> commentaries = new LinkedList<>();

    @ManyToMany
    private final List<User> userList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "subject")
    private final List<Note> noteList = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    private final List<SubjectModeratorRelationship> moderatorRelationshipList = new ArrayList<>();

    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<Commentary> getCommentaryList() {
        return commentaries;
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

    public void setScore(double score) {
        this.score = score;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<User> getUserList() {
        return userList;
    }

    public List<SubjectModeratorRelationship> getModeratorRelationshipList() {
        return moderatorRelationshipList;
    }
}
