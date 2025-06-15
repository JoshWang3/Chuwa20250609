package org.example;


import java.time.LocalDate;

public class Employee {
    private String name;
    private String ssn;
    private LocalDate dateOfBirth;
    private Address address;
    private Department department;
    private static final String company = "Good Company";

    public Employee(String name, String ssn, LocalDate dateOfBirth, Address address, Department department) {
        this.name = name;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.department = department;
    }

    public String getName() {
        return name;
    }
    public String getSsn() {
        return ssn;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public Address getAddress() {
        return address;
    }
    public Department getDepartment() {
        return department;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name: '" + name + '\'' +
                ", ssn: '" + ssn + '\'' +
                ", date of birth: " + dateOfBirth +
                ", address: " + address.toString() +
                ", department: " + department.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name) &&
                dateOfBirth.equals(employee.dateOfBirth) &&
                ssn.equals(employee.ssn) &&
                address.equals(employee.address) &&
                department.equals(employee.department);
    }

    public static String getCompanyName() {
        return "Company Name: " + company;
    }
}