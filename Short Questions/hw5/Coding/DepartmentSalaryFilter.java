import java.util.*;
import java.util.stream.*;

class Employee {
    String name;
    double salary;
    Employee(String name, double salary) { this.name = name; this.salary = salary; }
}

class Department {
    String name;
    List<Employee> employees;
    Department(String name, List<Employee> employees) {
        this.name = name; this.employees = employees;
    }
}

public class DepartmentSalaryFilter {
    public static void main(String[] args) {
        Department d1 = new Department("IT",
                Arrays.asList(new Employee("Alice", 120000), new Employee("Bob", 95000)));
        Department d2 = new Department("Finance",
                Arrays.asList(new Employee("Cathy", 110000), new Employee("Dan", 105000)));
        Department d3 = new Department("HR",
                Arrays.asList(new Employee("Eve", 70000), new Employee("Frank", 75000)));

        List<Department> departments = Arrays.asList(d1, d2, d3);

        List<String> richDepartments = departments.stream()
                .filter(d -> d.employees.stream().mapToDouble(e -> e.salary).average().orElse(0) > 100000)
                .map(d -> d.name)
                .collect(Collectors.toList());

        System.out.println("Departments with avg salary > 100,000: " + richDepartments);
    }
}
