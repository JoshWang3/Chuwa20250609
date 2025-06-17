package com.chuwa.assignment.utils;

import com.chuwa.assignment.pojos.*;

import java.util.Date;

/**
 * Demonstrates three core OOP concepts—Encapsulation, Polymorphism, and Inheritance
 */
public class OOPConcept {

    public static void main(String[] args) {

        /* ─────────────────────── 1. ENCAPSULATION ─────────────────────── */
        System.out.println("=== 1. Encapsulation (Student) ===");
        // All fields are private; we interact only through methods
        Student student = new Student("Angela", 1, 80);

        System.out.println("Initial state:");
        System.out.println("  Name  : " + student.getName());
        System.out.println("  ID    : " + student.getId());
        System.out.println("  Grade : " + student.getGrade());

        // Valid update
        System.out.println("\nSetting grade to 90 (valid)...");
        student.setGrade(90);
        System.out.println("  Updated Grade : " + student.getGrade());

        // Invalid update—Student class should handle/reject internally
        // Grade remains unchanged if validation fails
        System.out.println("\nAttempting to set grade to 101 (invalid)...");
        student.setGrade(101);
        System.out.println("  Grade After Attempt : " + student.getGrade());

        /* ─────────────────────── 2. POLYMORPHISM ─────────────────────── */
        System.out.println("\n=== 2. Polymorphism (Animal sounds) ===");
        Animal generic = new Animal();
        Animal cat     = new Cat();   // Up-casting
        Animal dog     = new Dog();   // Up-casting

        System.out.print("Generic animal says: "); generic.makeSound();
        System.out.print("Cat says           : "); cat.makeSound();
        System.out.print("Dog says           : "); dog.makeSound();

        /* ─────────────────────── 3. INHERITANCE ─────────────────────── */
        System.out.println("\n=== 3. Inheritance (User vs AdminUser) ===");
        User normalUser = new User("Angela", "passwd", "angela@mail.com");
        AdminUser admin = new AdminUser("Ja", "secret", "ja@mail.com", "SUPER");

        System.out.println("Logging in as normal user:");
        normalUser.login();

        System.out.println("\nLogging in as admin user:");
        admin.login();
        admin.accessAdminLevel();
    }
}
