// Employee.java
import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private static int employeeCount = 0;

    static {
        System.out.println("Employee class loaded. Current count = " + employeeCount);
    }

    private String     employeeName;
    private LocalDate  dateOfBirth;
    private Department department;
    private String     socialNumber;
    private Address    address;

    public Employee() {
        employeeCount++;
    }

    public Employee(String     employeeName,
                    LocalDate  dateOfBirth,
                    Department department,
                    String     socialNumber,
                    Address    address) {
        employeeCount++;
        this.employeeName = employeeName;
        this.dateOfBirth  = dateOfBirth;
        this.department   = department;
        this.socialNumber = socialNumber;
        this.address      = address;
    }

    public static int    getEmployeeCount()        { return employeeCount; }
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
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", dateOfBirth="  + dateOfBirth  +
                ", department="   + department   +
                ", socialNumber='" + socialNumber + '\'' +
                ", address="      + address      +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        Employee e = (Employee) o;
        return Objects.equals(employeeName, e.employeeName) &&
                Objects.equals(dateOfBirth,  e.dateOfBirth)  &&
                Objects.equals(department,   e.department)   &&
                Objects.equals(socialNumber, e.socialNumber) &&
                Objects.equals(address,      e.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeName, dateOfBirth, department, socialNumber, address);
    }

    public static void main(String[] args) {
        System.out.println("Before any instantiation: count = " + Employee.getEmployeeCount());
        Employee e1 = new Employee();
        System.out.println("After e1 created: count = " + Employee.getEmployeeCount());
        Employee e2 = new Employee("Andy",
                LocalDate.of(2000, 12, 31),
                new Department("HR"),
                "123123",
                new Address("123 Cass Pd", "City", "Zip"));
        System.out.println("After e2 created: count = " + Employee.getEmployeeCount());
        System.out.println("Is e2 adult? " + Employee.isAdult(e2));
    }
}
