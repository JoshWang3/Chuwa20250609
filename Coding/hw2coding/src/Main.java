import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Department and Address for emp1
        Department dept1 = new Department("Engineering");
        Address addr1 = new Address("123 Main St", "San Diego", "CA");

        // Employee 1
        Employee emp1 = new Employee("Alice", new Date(), dept1, "123-45-6789", addr1);
        // First time show count
        System.out.println(Employee.getCount());

        // Department and Address for emp2
        Department dept2 = new Department("HR");
        Address addr2 = new Address("456 Oak Ave", "Los Angeles", "CA");

        // Employee 2
        Employee emp2 = new Employee("Bob", new Date(), dept2, "987-65-4321", addr2);
        // Second time show count
        System.out.println(Employee.getCount());

        // Print both employees to show JVM
        System.out.println("=== Employee 1 ===");
        System.out.println(emp1);
        System.out.println("Heap reference (emp1): " + System.identityHashCode(emp1));
        System.out.println("Heap reference (emp1.department): " + System.identityHashCode(emp1.getDepartment()));
        System.out.println("Heap reference (emp1.address): " + System.identityHashCode(emp1.getAddress()));

        System.out.println("\n=== Employee 2 ===");
        System.out.println(emp2);
        System.out.println("Heap reference (emp2): " + System.identityHashCode(emp2));
        System.out.println("Heap reference (emp2.department): " + System.identityHashCode(emp2.getDepartment()));
        System.out.println("Heap reference (emp2.address): " + System.identityHashCode(emp2.getAddress()));


    }
}
