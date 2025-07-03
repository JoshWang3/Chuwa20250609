package com.example.stream;

import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    double salary;
    String department;

    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class Q2HighSalaryDepartments {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", 120000, "Engineering"),
                new Employee("Bob", 90000, "Sales"),
                new Employee("Charlie", 110000, "Engineering"),
                new Employee("Diana", 105000, "Finance")
        );

        List<String> result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream()
                .filter(e -> e.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Departments with average salary > 100000:");
        System.out.println(result);
    }
}
