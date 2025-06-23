import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        // Static can be called without any instance. because it belongs to the class not the object.
        System.out.println("Total number of employees: " + Employee.getTotalEmployee());

        // Create Employee Instances
        Employee eb1 = new Employee("Tom", "1990-09-09", "Data Science", "110-11-110", "San Jose");
        Employee eb2 = new Employee("Jerry", "1990-09-09", "Software Engineering", "110-11-111", "San Jose");
        Employee eb3 = new Employee("Tom", "1990-09-09", "Data Science", "110-11-110", "San Jose");

        // Print out Employees' Information
        System.out.println(eb1);
        System.out.println(eb2);
        System.out.println(eb3);

        // Check override .equals()
        System.out.println(eb1.equals(eb2)); // return ture
        System.out.println(eb1.equals(eb3)); // return false

        //  use reflection utilities to show how eb1 and eb2 and eb3's memory allocated in JVM
        System.out.println("eb1.hashcode:" + eb1.hashCode() + " eb2.hashcode" + eb2.hashCode());
        System.out.println("eb1.hashcode:" + eb1.hashCode() + " eb3.hashcode" + eb3.hashCode());
        System.out.println("eb2.hashcode:" + eb2.hashCode() + " eb3.hashcode" + eb3.hashCode());

        System.out.println("eb1.getClass().hashcode:" + eb1.getClass().hashCode() + " eb2.getClass().hashcode" + eb2.getClass().hashCode());
        System.out.println("eb1.getClass().hashcode:" + eb1.getClass().hashCode() + " eb3.getClass().hashcode" + eb3.getClass().hashCode());
        System.out.println("eb2.getClass().hashcode:" + eb2.getClass().hashCode() + " eb3.getClass().hashcode" + eb3.getClass().hashCode());

    }
}