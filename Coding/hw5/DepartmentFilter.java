package Coding.hw5;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {
    String name;
    String department;
    int salary;

    Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getName() {return name;}
    public String getDepartment() {return department;}
    public int getSalary() {return salary;}

}

public class DepartmentFilter {
    public static void main(String[] args) {
        List<Employee> list = Arrays.asList(new Employee("a", "hr", 120000),
                new Employee("b", "tech", 200000),
                new Employee("c", "hr", 80000),
                new Employee("d", "accounting", 150000),
                new Employee("e", "accounting", 80000),
                new Employee("f", "operations", 60000));

        List<String> ans = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getSalary)))
                .entrySet().stream()
                .filter(e -> e.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(ans);
    }
}
