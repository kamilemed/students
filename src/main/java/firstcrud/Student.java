package firstcrud;

import java.io.Serializable;
import java.util.Date;


public class Student implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Student() {}

    public Student(Long id, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public void update(Student student) {
        this.id = student.id;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.birthDate = student.birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
