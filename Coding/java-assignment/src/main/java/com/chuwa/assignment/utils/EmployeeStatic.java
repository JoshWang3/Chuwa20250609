package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.Employee;

public class EmployeeStatic {
    public static void main(String[] args) {

        // Before creating any object: static data already available
        System.out.println("Next ID (static): " + Employee.getNextId());

        // Create two Employee objects (instance data)
        Employee e1 = new Employee("Angela");
        Employee e2 = new Employee("Ja");

        // Print instance values
        System.out.println("\nAfter creating employees:");
        System.out.println("Employee{id='" + e1.getId() + "', name='" + e1.getName() + "'}"
        );
        System.out.println("Employee{id='" + e2.getId() + "', name='" + e2.getName() + "'}"
        );

        //System.out.println("(static)Employee{" + "id='" + e1.getNextId() + '\''+ "name='" + e1.getName() + '\''+ "'}");
        //System.out.println("(static)Employee{" + "id='" + e2.getNextId() + '\''+ "name='" + e2.getName() + '\''+ "'}");

        // Static data has now changed
        System.out.println("\nStatic nextId after e1 and e2: " + Employee.getNextId());
    }
}
