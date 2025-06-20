package org.hw5;

import java.util.*;
import java.util.stream.Collectors;

//1.2. Return names of departments where average employee salary > 100,000
public class DepartmentSalaryAnalyzer {

    // Employee class
    static class Employee {
        private String name;
        private String department;
        private double salary;

        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        // Getters
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return String.format("%s (%s): $%.2f", name, department, salary);
        }
    }

    /**
     * Returns names of departments where average employee salary > 100,000
     * @param employees List of employees
     * @return List of department names with avg salary > 100k
     */
    public static List<String> getDepartmentsWithHighAvgSalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Alternative approach with more explicit steps
     */
    public static List<String> getDepartmentsWithHighAvgSalaryVerbose(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment))
                .entrySet()
                .stream()
                .filter(entry -> {
                    double avgSalary = entry.getValue()
                            .stream()
                            .mapToDouble(Employee::getSalary)
                            .average()
                            .orElse(0.0);
                    return avgSalary > 100000;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Demo usage
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 120000),
                new Employee("Bob", "Engineering", 110000),
                new Employee("Charlie", "Sales", 80000),
                new Employee("Diana", "Sales", 85000),
                new Employee("Eve", "Marketing", 95000),
                new Employee("Frank", "Marketing", 105000),
                new Employee("Grace", "Finance", 130000),
                new Employee("Henry", "Finance", 125000),
                new Employee("Ivy", "HR", 70000),
                new Employee("Jack", "HR", 75000)
        );

        System.out.println("All employees:");
        employees.forEach(System.out::println);

        System.out.println("\nDepartments with average salary > $100,000:");
        List<String> highSalaryDepts = getDepartmentsWithHighAvgSalary(employees);
        highSalaryDepts.forEach(System.out::println);


    }
}