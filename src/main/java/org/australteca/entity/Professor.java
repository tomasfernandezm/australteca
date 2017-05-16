package org.australteca.entity;

import com.sun.istack.internal.NotNull;
//import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "PROFESSOR")
public class Professor extends AbstractEntity{

    //@Field
    @Column(name = "PROFESSOR_FNAME")
    private String firstName;

    //@Field
    @Column(name = "PROFESSOR_LNAME")
    private String lastName;

    @Column(name = "PROFESSOR_DEGREE")
    private String degree;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Subject> subjects = new ArrayList<Subject>();

    public Professor() {
    }

    public Professor(String firstName, String lastName, String degree) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(@NotNull Subject subject){
        subjects.add(subject);
    }
}
