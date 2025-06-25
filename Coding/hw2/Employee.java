package hw2;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String ssn;
    private HomeAddress homeAddress;

    // Static utility field
    private static int employeeCount = 0;


    public Employee() {

    }


    public Employee(String name, LocalDate dateOfBirth, Department department, String ssn, HomeAddress homeAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.homeAddress = homeAddress;
        employeeCount++;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "name=" + getName()
                + ", dateOfBirth=" + getDateOfBirth()
                + ", department=" + getDepartment()
                + ", ssn=" + getSsn()
                + ", homeAddress=" + getHomeAddress();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name)
                && Objects.equals(dateOfBirth, employee.dateOfBirth)
                && Objects.equals(department, employee.department)
                && Objects.equals(ssn, employee.ssn)
                && Objects.equals(homeAddress, employee.homeAddress);
    }
}
