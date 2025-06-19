package hw2;

import com.sun.tools.javac.Main;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    public Employee() {}

    public Employee(String name, LocalDate dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
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
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                ", ssn='" + ssn + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // Identity Check: The first check should always be for reference equality, which is a performance optimization
        if (this == o) return true;
        // Null Check & Type Check(make sure o parameter is an instance of the correct class)
        if (o == null || getClass() != o.getClass()) return false;
        // cast the o to your class type 将传入的Object o 强制转换为 Employee 类型
        Employee employee = (Employee) o;
        // Field Check：只有当两个 Employee 对象的所有field都相同时，才认为它们相等
        return Objects.equals(name, employee.name) && Objects.equals(dateOfBirth, employee.dateOfBirth) && Objects.equals(department, employee.department) && Objects.equals(ssn, employee.ssn) && Objects.equals(address, employee.address);
    }

    // when overriding equals(), hashcode() should be overridden as well
    // 如果两个对象通过 equals() 方法相等，那么它们的 hashCode() 必须返回相同值
    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth, department, ssn, address);
    }

    // A static method
    public static String staticMethod() {
        return "This is a static method";
    }
    // Can directly call static method using Class name without instantiation
    public static void main(String[] args) {
        System.out.println(Employee.staticMethod());
    }
}