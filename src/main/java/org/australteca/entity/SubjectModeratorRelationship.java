package org.australteca.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by tomi on 10/05/17.
 */
@Entity
public class SubjectModeratorRelationship extends AbstractEntity {

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private User user;

    private boolean accepted;

    public SubjectModeratorRelationship(){

    }

    public SubjectModeratorRelationship(Subject subject, User user) {
        this.subject = subject;
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void accept(){
        accepted = true;
    }

    public void decline(){
        accepted = false;
    }
}
