package firstcrud;

public class Student {

    private Long id;
    private String firstName;
    private String lastName;

    public Student() {}

    public Student( Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Student clone() {
        return new Student(id, firstName, lastName);
    }

    public void restore(Student student) {
        this.id = student.id;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
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
}
