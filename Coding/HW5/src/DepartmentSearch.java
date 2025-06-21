import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

class Employee{
    private double salary;

    public double getSalary() {
        return salary;
    }

    public Employee(double salary) {
        this.salary = salary;
    }
}
class Department{
    private String name;
    private List<Employee> employees;
    String getName(){
        return name;
    }
    List<Employee> getEmployees(){
        return employees;
    }

    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }
}
public class DepartmentSearch {
    static class Solution{
        public static List<String> findRichDepartments(List<Department> departments, double query) {
            return departments.stream()
                    .filter(d->{
                        if(d.getEmployees().isEmpty()) return false;
                        double sum=0;
                        for(Employee e:d.getEmployees()){
                            sum+=e.getSalary();
                        }
                        if(sum/d.getEmployees().size()>=query+1e-10) return true;
                        else return false;
                    })
                    .map(d->d.getName())
                    .collect(Collectors.toList());
        }
    }
    public static void main(String[] args) {
        Department engineering = new Department(
                "Engineering",
                Arrays.asList(
                        new Employee(120_000),
                        new Employee(110_000),
                        new Employee(130_000)
                )
        );

        Department hr = new Department(
                "Human Resources",
                Arrays.asList(
                        new Employee(80_000),
                        new Employee(90_000),
                        new Employee(85_000)
                )
        );

        Department sales = new Department(
                "Sales",
                Arrays.asList(
                        new Employee(100_000),
                        new Employee(105_000),
                        new Employee(102_000)
                )
        );

        Department emptyDept = new Department(
                "Vacant Dept",
                new ArrayList<>()
        );
        List<Department> allDepts = Arrays.asList(engineering, hr, sales, emptyDept);
        List<String> richDepts = Solution.findRichDepartments(allDepts,100_000);

        System.out.println(richDepts);

    }
}
