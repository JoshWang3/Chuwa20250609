package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.Department;
import com.chuwa.assignment.pojos.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NameOfDeptAboveSalary {
    // Return names of departments where average employee salary > 100,000
    public static void main(String[] args) {
        List<Department> deptList = List.of(
                new Department("Engineering", List.of(
                        new Employee("Ally", 200000),
                        new Employee("Carol", 220000)
                )),
                new Department("HR", List.of(
                        new Employee("Jenny", 100000),
                        new Employee("Kevin", 120000)
                )),
                new Department("RD", List.of(
                        new Employee("Kelly", 80000),
                        new Employee("Andrew", 82000)
                ))
        );
        List<String> depts = deptList.stream()
                .filter(d -> d.getEmployees().stream()  // Start Processing departments
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0) > 100000)    // Keep only department > 100000
                .map(Department::getName)            // Convert to department name
                .collect(Collectors.toList());// Collect dept names to list

        System.out.println(depts);
    }
}
