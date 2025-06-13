import java.util.Objects;

class Employee {
    private String name;
    private String dateOfBirth;
    private String department;
    private String socialSecurityNumber;
    private Address homeAddress;
    private static int employeeCount = 0;  // Static variable - shared across all instances


    public Employee(String name, String dateOfBirth, String department,
                    String socialSecurityNumber, Address homeAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialSecurityNumber = socialSecurityNumber;
        this.homeAddress = homeAddress;
        employeeCount++;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", department='" + department + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", homeAddress=" + homeAddress +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        return Objects.equals(name, other.name) &&
                Objects.equals(dateOfBirth, other.dateOfBirth) &&
                Objects.equals(department, other.department) &&
                Objects.equals(socialSecurityNumber, other.socialSecurityNumber) &&
                Objects.equals(homeAddress, other.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, department, socialSecurityNumber, homeAddress);
    }


    // Getter for demonstrating JVM Memory Allocation
    public Address getHomeAddress() {
        return homeAddress;
    }

    // Additional getter to demonstrate instance vs static access
    public String getName() {
        return name;
    }
}
