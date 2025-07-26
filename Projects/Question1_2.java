import java.util.*;
import java.util.stream.Collectors;


public class Question1_2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("A", "a", 6000),
                new Employee("B", "b", 2000),
                new Employee("C", "c", 9000),
                new Employee("D", "b", 1000),
                new Employee("E", "c", 12000),
                new Employee("F", "a", 5000),
                new Employee("G", "b", 3000),
                new Employee("K", "c", 10000),
                new Employee("I", "c", 10000)
        );


        System.out.println("Highest average slary department is: " + highAvgSalaryDepart(employees));


    }

    public static String highAvgSalaryDepart(List<Employee> employees) {



        String result = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        (Collectors.averagingInt(Employee::getSalary))))
                .entrySet().stream()
                .max(Comparator.comparingDouble(m -> m.getValue()))
                .map(m -> m.getKey())
                .orElse("")
                .toString();


        return result;
    }
}

class Employee{
    private String name;
    private String department;
    private int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}