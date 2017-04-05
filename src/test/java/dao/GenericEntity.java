package dao;

import javax.persistence.*;

/**
 * Created by tomi on 29/03/17.
 */

@Table(name = "ENTITY")
@Entity
public class GenericEntity {

    @Id @GeneratedValue
    @Column(name = "ENTITY_ID")
    private int id;

    @Column(name = "ENTITY_FNAME")
    private String firstName;

    @Column(name = "ENTITY_LNAME")
    private String lastName;

    @Column(name = "ENTITY_AGE")
    private int age;

    public GenericEntity() {
    }

    public GenericEntity(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(GenericEntity other){
        return other.id == id;
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

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
