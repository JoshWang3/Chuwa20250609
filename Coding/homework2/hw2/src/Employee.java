import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String employeeName;
    private LocalDate dateOfBirth;
    private Department department;
    private String socialNumber;
    private String homeAddress;

    public Employee() {
    }

    public Employee(String employeeName,
                    LocalDate dateOfBirth,
                    Department department,
                    String socialNumber,
                    String homeAddress) {
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialNumber = socialNumber;
        this.homeAddress = homeAddress;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public void setsocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                ", socialSecurityNumber='" + socialNumber + '\'' +
                ", homeAddress=" + homeAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee epy = (Employee) o;
        return Objects.equals(employeeName, epy.employeeName) &&
                Objects.equals(dateOfBirth, epy.dateOfBirth) &&
                Objects.equals(department, epy.department) &&
                Objects.equals(socialNumber, epy.socialNumber) &&
                Objects.equals(homeAddress, epy.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, dateOfBirth, department, socialNumber, homeAddress);
    }
}
