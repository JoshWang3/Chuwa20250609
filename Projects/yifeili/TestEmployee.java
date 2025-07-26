package yifeili;

import java.time.LocalDate;

public class TestEmployee {
    public static void main(String[] args) {
        // Q1, testing 3 classes and corresponding functions
        Department dept = new Department("Engineering");
        Address addr = new Address("123 Main St", "Seattle", "WA", 98101);

        Employee e1 = new Employee("Alice", LocalDate.of(1990, 5, 15), dept, "123-45-6789", addr);
        Employee e2 = new Employee("Bob", LocalDate.of(1985, 3, 10), new Department("HR"), "987-65-4321",
                new Address("456 Oak St", "New York", "NY", 10001));
        Employee e3  = new Employee("Bob", LocalDate.of(1985, 3, 10), new Department("HR"), "987-65-4321",
                new Address("456 Oak St", "New York", "NY", 10001));
        Employee e4 = e2;
        System.out.println("Employee 1:");
        System.out.println(e1);
        System.out.println("Employee 2:");
        System.out.println(e2);

        System.out.println("Employee 3 compare e2");
        System.out.println(e2.equals(e3));
        System.out.println("Employee 3 compare e2");
        System.out.println(e2.equals(e4));

        //Q2:
        //Check hashscode to identify if they are pointed to a same memory
        System.out.println("e1 hash: " + System.identityHashCode(e1));
        System.out.println("e2 hash: " + System.identityHashCode(e2));
        System.out.println("e3 hash: " + System.identityHashCode(e3));
        System.out.println("e4 hash: " + System.identityHashCode(e4));
        System.out.println("e1.department hash: " + System.identityHashCode(e1.getDepartment()));
        System.out.println("e2.department hash: " + System.identityHashCode(e2.getDepartment()));
        System.out.println("e3.department hash: " + System.identityHashCode(e3.getDepartment()));
        System.out.println("e4.department hash: " + System.identityHashCode(e4.getDepartment()));
        System.out.println("e1.address hash: " + System.identityHashCode(e1.getAddress()));
        System.out.println("e2.address hash: " + System.identityHashCode(e2.getAddress()));
        System.out.println("e3.address hash: " + System.identityHashCode(e3.getAddress()));
        System.out.println("e4.address hash: " + System.identityHashCode(e4.getAddress()));

        //Q3. static method:
        System.out.println("by checking Employee object, employee count now is " + Employee.getEmployCount());
        System.out.println("by checking employee3, employee count now is " + e1.getEmployCount());
        Employee e3_cp = e3;
        System.out.println("we created a copy of employee3, employee count now is " + e3_cp.getEmployCount());

        Employee e_new  = new Employee("Bob", LocalDate.of(1985, 3, 10), new Department("HR"), "987-65-4321",
                new Address("456 Oak St", "New York", "NY", 10001));
        System.out.println("We created a new employee, now employee count now is " + e_new.getEmployCount());


    }
}
