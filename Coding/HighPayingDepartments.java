package Coding;
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

public class HighPayingDepartments {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "HR", 95000),
                new Employee("Bob", "Engineering", 120000),
                new Employee("Charlie", "Engineering", 130000),
                new Employee("Daisy", "HR", 105000),
                new Employee("Eve", "Sales", 99000)
        );

        List<String> departments = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(departments); // 示例输出：[Engineering, HR]
    }
}
