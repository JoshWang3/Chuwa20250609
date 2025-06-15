package hw2;

import java.util.Objects;
import java.util.Date;

public class Employee {
    private String name;
    private Date dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    private static int employeeCount = 0;

    public Employee(String name, Date dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
        employeeCount++;
    }
    public static int getEmployeeCount() {
        return employeeCount;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Employee {" +
                "\n  Name = '" + name + '\'' +
                ",\n  DOB = " + dateOfBirth +
                ",\n  Department = " + department +
                ",\n  SSN = '" + ssn + '\'' +
                ",\n  Address = " + address +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(ssn, employee.ssn) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, department, ssn, address);
    }
}