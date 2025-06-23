// Employee Class

import java.util.Objects;

public class Employee {
    private String name;
    private String dateOfBirth;
    private String department;
    private String ssn;
    private String address;

    private static int totalEmployee = 0;

    public Employee(String name, String dateOfBirth, String department, String ssn, String address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee Information: " + "\n" +
                "Name=" + name + "\n" +
                "Date of Birth=" + dateOfBirth + "\n" +
                "Department=" + department + "\n" +
                "SSN=" + ssn + "\n" +
                "Address=" + address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Employee.class) {
            return false;
        }
        Employee e = (Employee) obj;
        return Objects.equals(name, e.name) &&
                Objects.equals(dateOfBirth, e.dateOfBirth) &&
                Objects.equals(department, e.department) &&
                Objects.equals(ssn, e.ssn) &&
                Objects.equals(address, e.address);
    }


    public static int getTotalEmployee() {
        return totalEmployee;
    }
}
