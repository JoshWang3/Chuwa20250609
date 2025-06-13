package org.example;

import java.time.LocalDate;

public class StaticContentDemo {
    public static void main(String[] args) throws Exception {



        System.out.println("1. STATIC CONTENT LOADING (Before any instance creation)");
        System.out.println("   Testing static method WITHOUT creating any Employee:");
        System.out.println("   Employee.isValidSSN(\"123-45-6789\"): " +
                Employee.isValidSSN("123-45-6789"));
        System.out.println("   Employee.isValidSSN(\"invalid\"): " +
                Employee.isValidSSN("invalid"));
        System.out.println("   Employee.getEmployeeCount(): " +
                Employee.getEmployeeCount());
        System.out.println("   -> Static methods work without any instance!\n");

        // Create instances and show the difference
        System.out.println("2. CREATING EMPLOYEE INSTANCES");
        Employee emp1 = new Employee("John Doe", LocalDate.of(1990, 5, 15),
                "Engineering", "123-45-6789",
                "123 Main St");

        Employee emp2 = new Employee("Jane Smith", LocalDate.of(1985, 8, 22),
                "Marketing", "987-65-4321",
                "456 Oak Ave");

        System.out.println("\n   Current employee count: " + Employee.getEmployeeCount());
        System.out.println("   -> Static variable shared across all instances\n");



        // Memory comparison
        System.out.println("\n3. MEMORY LOCATION COMPARISON");
        System.out.println("   Class object location:");
        System.out.println("   Employee.class hashCode: " + Employee.class.hashCode());
        System.out.println("   emp1.getClass() hashCode: " + emp1.getClass().hashCode());
        System.out.println("   emp2.getClass() hashCode: " + emp2.getClass().hashCode());
        System.out.println("   -> All refer to same Class object in Method Area");

        System.out.println("\n   Instance object locations:");
        System.out.println("   emp1 identity hashCode: " + System.identityHashCode(emp1));
        System.out.println("   emp2 identity hashCode: " + System.identityHashCode(emp2));
        System.out.println("   -> Different objects in Heap");



        // Static content is stored in Method Area/Metaspace
        // Instance content is stored in Heap with each object
    }
}
