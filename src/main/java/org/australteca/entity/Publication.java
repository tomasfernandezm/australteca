package org.australteca.entity;

import org.australteca.Constants;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomi on 29/04/17.
 */

@Entity
public class Publication extends AbstractEntity{

    private String name;

    @ManyToOne
    private User author;

    @ManyToMany
    private List<User> suscribedUsers = new ArrayList<>();

    private String requirements;
    private String description;
    private String role;
    private String tasks;

    public Publication(){

    }

    public Publication(String name, User author, String description, String requirements, String tasks, String role) {
        this.name = name;
        this.author = author;
        this.requirements = requirements;
        this.description = description;
        this.tasks = tasks;
        this.role = role;
    }

    public List<User> getSuscribedUsers() {
        return suscribedUsers;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User contact) {
        this.author = contact;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuscribedUsers(List<User> suscribedUsers) {
        this.suscribedUsers = suscribedUsers;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}
