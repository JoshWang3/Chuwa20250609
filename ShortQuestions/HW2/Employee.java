
import java.time.LocalDate;

public class Employee {

    // Static variable shared by all Employee instances
    private static int employeeCount = 0;

    // Instance fields
    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    // Constructor
    public Employee(String name, String ssn, LocalDate dateOfBirth, Department department, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
        employeeCount++;
    }

    // Static utility method
    public static void printCompanyPolicy() {
        System.out.println("All employees must follow the Magic World rules!");
    }

    // Static getter
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

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSsn() {
        return ssn;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return  "name = '" + name + '\'' +
                ", dateOfBirth = " + dateOfBirth +
                ", department = " + department +
                ", ssn = " + ssn +
                ", address = " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                department.equals(employee.department) &&
                ssn.equals(employee.ssn) &&
                address.equals(employee.address);
    }
}

