package hw5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeSalary {
    public static void main(String[] args) {
        // initialize an arraylist for employee
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Alice", "IT", 120000));
        employeeList.add(new Employee("Bob", "IT", 110000));
        employeeList.add(new Employee("Charlie", "IT", 90000));
        employeeList.add(new Employee("David", "HR", 95000));
        employeeList.add(new Employee("Eva", "HR", 98000));
        employeeList.add(new Employee("Frank", "Finance", 130000));
        employeeList.add(new Employee("Grace", "Finance", 125000));

        // group by department and calculate average salary
        Map<String, Double> avgSalaryByDep = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        // print each department with their average salary
        // avgSalaryByDep.forEach((key, value) -> System.out.println(key + " : " + value));

        // filter the department where average employee salary > 100,000
        // turn the map to stream: need to use .entrySet()
        // in the stream: each element is Map.Entry<String, Double> object, like Entry("IT", 106666.66)
        List<String> filteredDep = avgSalaryByDep.entrySet().stream()
                .filter(entry -> entry.getValue() > 100000)
                // mapping each Map.Entry object to its key
                // so each element in stream becomes Stream<String>："IT", "Finance"
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        filteredDep.forEach(System.out::println);
    }
}
