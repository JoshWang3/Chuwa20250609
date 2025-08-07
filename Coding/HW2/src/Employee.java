import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private String socialSecurityNumber;
    private Department department;
    private Address address;
    private static int employeeCount = 0;

    public Employee(String name, LocalDate dateOfBirth, String socialSecurityNumber,
                    Department department, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.department = department;
        this.address = address;
        employeeCount++;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    @Override
    public String toString() {
        return "Employee: " + name + "\n" +
                "  Date of Birth: " + dateOfBirth + "\n" +
                "  SSN: " + socialSecurityNumber + "\n" +
                "  Department: " + department + "\n" +
                "  Address: " + address + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return Objects.equals(name, employee.name) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(address, employee.address);
    }

}
