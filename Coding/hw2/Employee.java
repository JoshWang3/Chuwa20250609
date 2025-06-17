package hw2;
import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDate birthday;
    private Department department;
    private String ssn;
    private Address homeAddress;

    // constructor
    public Employee(String name, LocalDate birthday, Department department, String ssn, Address homeAddress) {
        this.name = name;
        this.birthday = birthday;
        this.department = department;
        this.ssn = ssn;
        this.homeAddress = homeAddress;
    }

    // Getters
    public String getName() { return name; }
    public LocalDate getDateOfBirth() { return birthday; }
    public Department getDepartment() { return department; }
    public String getSsn() { return ssn; }
    public Address getHomeAddress() { return homeAddress; }

    // Static method
    public static String staticMethod() {
        return "This is a static method";
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", department=" + department +
                ", ssn='" + ssn + '\'' +
                ", homeAddress=" + homeAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(birthday, employee.birthday) && Objects.equals(department, employee.department) && Objects.equals(ssn, employee.ssn) && Objects.equals(homeAddress, employee.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, department, ssn, homeAddress);
    }
}