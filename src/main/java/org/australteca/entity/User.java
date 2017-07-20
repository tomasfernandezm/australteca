package org.australteca.entity;

import org.australteca.Constants;

import javax.persistence.*;
import java.util.*;

import static org.australteca.Constants.*;

/**
 * Created by tomi on 22/03/17.
 */

@Entity
public class User extends AbstractEntity{

    private String firstName;
    private String lastName;

    @Embedded
    private Photo photo;

    @Column(unique = true, nullable = false)
    private String email;

    private String course;
    private String password;
    private String role;

    private int amountOfNotes = 0;

    @ManyToMany
    private final Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    private final Set<Publication> publications = new HashSet<>();

    @ElementCollection
    private final Map<String, Integer> subjectScores = new HashMap<>();

    @OneToMany(orphanRemoval = true)
    private final Set<Commentary> commentaries = new HashSet<>();

    @OneToMany
    private final List<SubjectModeratorRelationship> subjectModeratorRelationships = new ArrayList<>();

    public User() {}

    public User(String firstName, String lastName, String email, String course,
                String password, boolean moderator, boolean admin) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.password = password;
        assignRole(moderator, admin);
    }

    public void noteUploaded(){
        amountOfNotes++;
    }

    public int getAmountOfNotes() {
        return amountOfNotes;
    }

    public void setAmountOfNotes(int amountOfNotes) {
        this.amountOfNotes = amountOfNotes;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public Map<String, Integer> getSubjectScores() {
        return subjectScores;
    }

    public void makeAdmin(){
        role = ADMINISTRATOR;
    }

    public void makeModerator(){
        role = MODERATOR;
    }

    public void makeStandard(){
        role = STANDARD;
    }

    public boolean isStandard(){
        return role.equals(STANDARD);
    }

    public boolean isModerator(){
        return role.equals(MODERATOR);
    }

    public boolean isAdmin(){
        return role.equals(ADMINISTRATOR);
    }

    private void assignRole(boolean moderator, boolean admin){
        if(admin) role = ADMINISTRATOR;
        else if(moderator) role = Constants.MODERATOR;
        else role = Constants.STANDARD;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public Set<Commentary> getCommentaries() {
        return commentaries;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] data, String contentType, String ext){
        photo = new Photo(data, contentType, ext);
    }

    public List<SubjectModeratorRelationship> getSubjectModeratorRelationships() {
        return subjectModeratorRelationships;
    }
}
