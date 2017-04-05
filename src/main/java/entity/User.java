package entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by tomi on 22/03/17.
 */

@Entity
@Table(name = "USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private Integer id;

    @Column(name = "USER_FNAME")
    private String firstName;

    @Column(name = "USER_LNAME")
    private String lastName;

    @Column(name = "USER_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "USER_COURSE")
    private String course;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_ROLE")
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    private final Set<Subject> subjects = new HashSet<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final Set<Commentary> commentaries = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String course,
                String password, boolean moderator, boolean admin) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.password = password;
        assignRole(moderator, admin);
    }

    public void makeAdmin(){
        role = EntityConstants.ADMINISTRATOR;
    }

    public void makeModerator(){
        role = EntityConstants.MODERATOR;
    }

    public void makeStandard(){
        role = EntityConstants.STANDARD;
    }

    public boolean isStandard(){
        return role.equals(EntityConstants.STANDARD);
    }

    public boolean isModerator(){
        return role.equals(EntityConstants.MODERATOR);
    }

    public boolean isAdmin(){
        return role.equals(EntityConstants.ADMINISTRATOR);
    }

    private void assignRole(boolean admin, boolean moderator){
        if(admin) role = EntityConstants.ADMINISTRATOR;
        else if(moderator) role = EntityConstants.MODERATOR;
        else role = EntityConstants.STANDARD;
    }

    public Integer getId() {
        return id;
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
}
