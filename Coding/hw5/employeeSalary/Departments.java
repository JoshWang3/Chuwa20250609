package hw5.employeeSalary;

import java.util.Map;

public class Departments {
    public static final Map<String, Department> DEPARTMENT_MAP = Map.of("HR", new Department("HR"), "Engineering", new Department("Engineering"), "Finance", new Department("Finance"), "Marketing", new Department("Marketing"));
}
