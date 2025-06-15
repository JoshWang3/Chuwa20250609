package org.example;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        Department depart1 = new Department("IT");
        Address addr1 = new Address("5th Avenue", "New York", "NY", "90000", "USA");
        Employee emp1 = new Employee("Alice", "123456789", LocalDate.of(1990, 9, 12), addr1, depart1);

        Address addr2 = new Address("8th Avenue", "New York", "NY", "90000", "USA");
        Department depart2 = new Department("Accounting");
        Employee emp2 = new Employee("Eric", "234567890", LocalDate.of(1988, 3, 20), addr2, depart2);

        System.out.println(emp1.toString());
        System.out.println(emp2.toString());

        System.out.println(Employee.getCompanyName());

        //Use reflection to inspect their internal fields
        System.out.println("---- Reflecting emp1 ----");
        inspectObject(emp1);

        System.out.println("\n---- Reflecting emp2 ----");
        inspectObject(emp2);
    }

    public static void inspectObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        System.out.println("Class: " + clazz.getName());

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println("Field: " + field.getName() + " = " + value);
        }
    }
}
