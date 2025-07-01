package hw5;

import java.util.Arrays;
import java.util.List;

/**
 * Return names of departments where average employee salary > 100,000
 */
public class DepartmentFilter {
    public static void main(String[] args) {
        List<Department> deptList = List.of(
                new Department("Engineering", Arrays.asList(
                        new Employee("Alice", 120000),
                        new Employee("Bob", 110000)
                )),
                new Department("Sales", Arrays.asList(
                        new Employee("Helen", 150000),
                        new Employee("John", 100000),
                        new Employee("Michael", 80000)
                )),
                new Department("HR", Arrays.asList(
                        new Employee("Mike", 65000),
                        new Employee("Linda", 80000)
                )),
                new Department("Finance", Arrays.asList(
                        new Employee("Jessy", 95000),
                        new Employee("Crystal", 85000)
                ))
        );

        List<String> result = deptList.stream()
                .filter(d -> d.getEmployees().stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .orElse(0) > 100000)
                .map(Department::getDeptName)
                .toList();

        System.out.println("Departments with average salary > 100,000: " + result);
    }

}
