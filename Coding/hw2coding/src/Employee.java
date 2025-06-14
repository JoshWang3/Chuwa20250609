import java.util.*;

public class Employee {
    private String name;
    private Date dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    private static int count = 0;
    public Employee() {}

    public Employee(String name, Date dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;

        count++;
    }

    public static int getCount() {
        return count;
    }
    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public String getSsn() {
        return ssn;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee: " + name + "\n" +
                "DOB: " + dateOfBirth + "\n" +
                "Department: " + department + "\n" +
                "SSN: " + ssn + "\n" +
                "Address: " + address;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        return name.equals(other.name)
                && dateOfBirth.equals(other.dateOfBirth)
                && ssn.equals(other.ssn)
                && department.equals(other.department)
                && address.equals(other.address);
    }
}

