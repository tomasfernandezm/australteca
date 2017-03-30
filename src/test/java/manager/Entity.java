package manager;

import javax.persistence.*;

/**
 * Created by tomi on 29/03/17.
 */

@Table(name = "ENTITY")
@javax.persistence.Entity
public class Entity {

    @Id
    @Column(name = "ENTITY_ID")
    private int id;

    @Column(name = "ENTITY_FNAME")
    private String firstName;

    @Column(name = "ENTITY_LNAME")
    private String lastName;

    @Column(name = "ENTITY_AGE")
    private int age;

    Entity() {
    }

    Entity(Integer id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public boolean equals(Entity other){
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
