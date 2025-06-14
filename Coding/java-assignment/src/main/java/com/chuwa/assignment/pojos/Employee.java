package com.chuwa.assignment.pojos;

import java.util.*;

public class Employee {
    // Static: shared by all instances
    private static int nextId = 1;
//    private static String empName;
    private int id;
    private String name;
    private Date birthDate;
    private Department department;
    private String socialSecurityNumber;
    private Address address;

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee(String name){
        this.id = nextId++;
        this.name = name;
//        this.empName = empName;
    }

    //Constructor
    public Employee(int id, String name, Date birthDate, Department department, String socialSecurityNumber, Address address){
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.department = department;
        this.socialSecurityNumber = socialSecurityNumber;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='"  + id + '\'' +
                "name='" + name +'\'' +
                ", date of birth='" + birthDate + '\'' +
                ", department='" + department + '\'' +
                ", ssn='" + socialSecurityNumber + '\'' +
                ", address='" + address + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //If the passed object is the same object
        if (o == null ) return false;    // Null check
        if(!(o instanceof Employee)) return false; // Type check
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
                Objects.equals(address, employee.address);

    }
}