package org.australteca.entity;

import com.sun.istack.internal.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 29/03/17.
 */

@Entity
@Table(name = "PROFESSOR")
public class Professor extends AbstractEntity{

    @Column(name = "PROFESSOR_FNAME")
    private String firstName;

    @Column(name = "PROFESSOR_LNAME")
    private String lastName;

    @Column(name = "PROFESSOR_EMAIL")
    private String email;

    @Column(name = "PROFESSOR_INFORMATION")
    private  String information;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Subject> subjects = new ArrayList<Subject>();

    public Professor() {
    }

    public Professor(String firstName, String lastName, String email, String information) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.information = information;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(@NotNull Subject subject){
        subjects.add(subject);
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
