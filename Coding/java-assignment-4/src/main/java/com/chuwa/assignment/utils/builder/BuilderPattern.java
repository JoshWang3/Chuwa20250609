package com.chuwa.assignment.utils.builder;
import java.time.LocalDate;
public class BuilderPattern {
    public static void main(String[] args) {
        System.out.println("\n=== Builder Pattern (Employee HR) ===\n");

        Employee angela = new Employee.Builder(1, "Angela Huang")
                .title("SDE")
                .hireDate(LocalDate.of(2025, 6, 1))
                .salary(50_000)
                .build();

        Employee bob = new Employee.Builder(2, "Bob Chen")
                .title("Intern")
                .salary(25_000)
                .build();

        System.out.println(angela);
        System.out.println(bob);
    }
}

// Explanation
//
// • Builder lets us construct complex objects with readable, chained calls
// while keeping Employee immutable.
// • Required fields (id, name) enforced in Builder constructor.
// • Optional fields (title, hireDate, salary) have sensible defaults
//   and can be set fluently.
// • The final build() method returns a fully‑initialized, immutable
//   Employee instance.
// • Dates are formatted using a friendly "MMM dd, yyyy" pattern.