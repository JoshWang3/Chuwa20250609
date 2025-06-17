import java.time.LocalDate;

public class StaticDemo {
    public static void main(String[] args) {
        System.out.println("Employee count before instantiation: " + Employee.getEmployeeCount());

        Employee emp1 = new Employee("Alice", LocalDate.of(1990, 1, 1),
                new Department("Engineering", "ENG"),
                "123-45-6789",
                new Address("123 A St", "Seattle", "WA", "98101"));

        Employee emp2 = new Employee("Bob", LocalDate.of(1985, 5, 20),
                new Department("HR", "HR01"),
                "987-65-4321",
                new Address("456 B St", "Boston", "MA", "02110"));

        System.out.println("Employee count after instantiation: " + Employee.getEmployeeCount());

        System.out.println("SSN '123-45-6789' valid? " + Employee.isValidSSN("123-45-6789"));
        System.out.println("SSN 'INVALID' valid? " + Employee.isValidSSN("INVALID"));
    }
}
