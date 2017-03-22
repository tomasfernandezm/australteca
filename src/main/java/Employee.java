import javax.persistence.*;

/**
 * Created by tomi on 19/03/17.
 */
@Entity
@Table (name = "EMPLOYEE")
public class Employee {

    @Id @GeneratedValue
    @Column (name = "ID")
    private int id;

    @Column (name = "FNAME")
    private String firstName;

    @Column (name = "LNAME")
    private String lastName;

    @Column (name = "SALARY")
    private int salary;

    public Employee(){

    }

    public Employee(String firstName, String lastName, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}