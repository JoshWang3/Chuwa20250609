import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    // Static field shared across all Employee instances
    private static int employeeCount = 0;

    // Static initializer block runs once when the class is loaded
    static {
        System.out.println("Employee class loaded. Current count = " + employeeCount);
    }

    private String employeeName;
    private LocalDate dateOfBirth;
    private Department department;
    private String socialNumber;
    private String homeAddress;

    // No-arg constructor increments static counter
    public Employee() {
        employeeCount++;
    }

    public Employee(String employeeName,
                    LocalDate dateOfBirth,
                    Department department,
                    String socialNumber,
                    String homeAddress) {
        employeeCount++;
        this.employeeName = employeeName;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialNumber = socialNumber;
        this.homeAddress = homeAddress;
    }

    // Static utility: get total number of Employee instances created
    public static int getEmployeeCount() {
        return employeeCount;
    }

    // Static utility: check if an Employee is adult (18+ years old)
    public static boolean isAdult(Employee e) {
        return e.dateOfBirth != null &&
               LocalDate.now().minusYears(18).isAfter(e.dateOfBirth);
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

    public void setSocialNumber(String socialNumber) {
        this.socialNumber = socialNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
               "employeeName='" + employeeName + '\'' +
               ", dateOfBirth=" + dateOfBirth +
               ", department=" + department +
               ", socialNumber='" + socialNumber + '\'' +
               ", homeAddress='" + homeAddress + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return Objects.equals(employeeName, e.employeeName) &&
               Objects.equals(dateOfBirth, e.dateOfBirth) &&
               Objects.equals(department, e.department) &&
               Objects.equals(socialNumber, e.socialNumber) &&
               Objects.equals(homeAddress, e.homeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, dateOfBirth, department, socialNumber, homeAddress);
    }

    // Demonstration main method
    public static void main(String[] args) {
        System.out.println("Before any instantiation: count = " + Employee.getEmployeeCount());
        Employee e1 = new Employee();
        System.out.println("After e1 created: count = " + Employee.getEmployeeCount());
        Employee e2 = new Employee("Andy", LocalDate.of(2000, 12, 31), new Department("HR"), "123123", "123 Cass Pd");
        System.out.println("After e2 created: count = " + Employee.getEmployeeCount());

        System.out.println("Is e2 adult? " + Employee.isAdult(e2));
    }
}
