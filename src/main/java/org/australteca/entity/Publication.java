package org.australteca.entity;

import org.australteca.Constants;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by tomi on 29/04/17.
 */

@Entity
public class Publication extends AbstractEntity{

    private String name;

    @ManyToOne
    private User author;

    private String requirements;
    private String description;
    private String role;

    public Publication(){

    }

    public Publication(String name, User author, String requirements, String description, String role) {
        this.name = name;
        this.author = author;
        this.requirements = requirements;
        this.description = description;
        this.role = role;
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
}
