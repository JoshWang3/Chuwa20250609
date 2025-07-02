import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

class Department {
    String name;
    List<Employee> employees;

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class DepartmentsByAvgSalary {
    public static List<String> departmentsWithHighAvgSalary(List<Department> departments) {
        return departments.stream()
                .filter(d -> d.getEmployees().stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0) > 100000)
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Department> departments = List.of(
                new Department("Engineering", List.of(
                        new Employee("Alice", 120000),
                        new Employee("Bob", 130000))),
                new Department("Marketing", List.of(
                        new Employee("Eve", 90000),
                        new Employee("Charlie", 95000)))
        );
        System.out.println(departmentsWithHighAvgSalary(departments));
    }
}
