package org.australteca.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "SUBJECT")
public class Subject extends AbstractEntity{

    @OneToMany
    private final List<Commentary> commentaryList = new ArrayList<Commentary>();

    @Column (name = "SUBJECT_SCORE")
    private double score;

    @Column (name = "SUBJECT_AMOUNT_OF_SCORES")
    private double amountOfScores;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Column (name = "SUBJECT_NAME", unique = true)
    @Analyzer(definition = "subjectAnalyzer")
    private String subjectName;

    @IndexedEmbedded
    @ManyToMany
    private final List<Professor> professors = new ArrayList<>();

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "subject")
    private final List<Commentary> commentaries = new ArrayList<>();

    @ManyToMany
    private final List<User> userList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "subject")
    private final List<Note> noteList = new ArrayList<>();

    public Subject() {
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
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

    public void setScore(double score) {
        this.score = score;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<User> getUserList() {
        return userList;
    }
}
