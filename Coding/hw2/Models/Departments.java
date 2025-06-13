package hw2.Models;

import java.util.Map;

// this is a constants holder class
public class Departments {
    public static final Map<String, Department> DEPARTMENT_MAP = Map.of("HR", new Department("HR"), "Engineering", new Department("Engineering"), "Finance", new Department("Finance"), "Marketing", new Department("Marketing"));
}