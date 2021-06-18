public class Student {
    private String firstName;
    private String lastName;
    private String stdNumber;

    public Student() {
    }

    public Student(String firstName, String lastName, String stdNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.stdNumber = stdNumber;
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

    public String getStdNumber() {
        return stdNumber;
    }

    public void setStdNumber(String stdNumber) {
        this.stdNumber = stdNumber;
    }
}
