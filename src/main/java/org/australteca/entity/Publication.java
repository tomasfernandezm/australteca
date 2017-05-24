package org.australteca.entity;

import javax.persistence.Entity;

/**
 * Created by tomi on 29/04/17.
 */

@Entity
public class Publication extends AbstractEntity{

    private String name;
    private String contact;
    private String requirements;
    private String description;
    private boolean isWork;
    private boolean isPublication;

    public Publication(String name, String contact, String requirements, String description, boolean isWork, boolean isPublication) {
        this.name = name;
        this.contact = contact;
        this.requirements = requirements;
        this.description = description;
        this.isWork = isWork;
        this.isPublication = isPublication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    public boolean isPublication() {
        return isPublication;
    }

    public void setPublication(boolean publication) {
        isPublication = publication;
    }
}
