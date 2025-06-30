import java.util.*;
import java.util.stream.Collectors;

public class DepartmentSalary {
    private static final Map<String, Integer> departmentSalaries = Map.of(
            "Engineering", 115000,
            "Data Science", 110000,
            "Product", 105000,
            "Design", 95000,
            "Marketing", 92000,
            "Sales", 98000,
            "Finance", 102000,
            "HR", 91000,
            "Operations", 94000,
            "IT Support", 97000
    );

    public static void main(String[] args) {
        List<String> topDepartments = departmentSalaries.entrySet().stream()
                .filter(entry -> entry.getValue() > 100000)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(topDepartments);
    }
}
