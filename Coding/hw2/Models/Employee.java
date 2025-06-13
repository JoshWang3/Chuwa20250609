package hw2.Models;

import java.util.Date;

public class Employee {
    private static int totalEmployees = 0;

    private String name;
    private Date dateOfBirth;
    private Department department;
    private String SSN;
    private Address address;

    public Employee(String name, Date dateOfBirth, Department department, String SSN, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.SSN = SSN;
        this.address = address;
    }

    public static int getTotalEmployees() { return totalEmployees; }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public String getSSN() {
        return SSN;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee name: " + name + ", dateOfBirth: " + dateOfBirth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Employee employee = (Employee) obj;
        return this.name.equals(employee.getName()) && (this.dateOfBirth.equals(dateOfBirth)) && this.SSN.equals(employee.getSSN()) && this.department.equals(employee.getDepartment()) && this.address.equals(employee.getAddress());
    }
}