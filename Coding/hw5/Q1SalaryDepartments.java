import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class Q1SalaryDepartments {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 120000),
                new Employee("Bob", "Engineering", 110000),
                new Employee("Carol", "HR", 95000),
                new Employee("Dan", "HR", 97000),
                new Employee("Eve", "Sales", 101000)
        );

        List<String> result = employees.stream()
                .collect(Collectors.groupingBy(e -> e.department,
                        Collectors.averagingDouble(e -> e.salary)))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
