import java.util.Objects;

class Employee {
    private String name;
    private String dateOfBirth;
    private String department;
    private String socialSecurityNumber;
    private Address homeAddress;

    public Employee(String name, String dateOfBirth, String department,
                    String socialSecurityNumber, Address homeAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialSecurityNumber = socialSecurityNumber;
        this.homeAddress = homeAddress;
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
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.name) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
                Objects.equals(homeAddress, employee.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, department, socialSecurityNumber, homeAddress);
    }

    // Getter for demonstrating JVM Memory Allocation
    public Address getHomeAddress() {
        return homeAddress;
    }
}
