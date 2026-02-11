HW2

1. Write a Java POJO (plain old java object) named "Employee", inside Employ class, you should have

1. Employee's name
2. Employee's Date of Birth
3. Employee's Department (it can be another POJO)
4. Employee's Social Security Number
5. Employee's home address (it can be another POJO)

```java
// Address.java
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;

    // Constructors, getters, setters

    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        if (!city.equals(address.city)) return false;
        if (!state.equals(address.state)) return false;
        return zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode.hashCode();
        return result;
    }
}

```

```java
// Department.java
public class Department {
    private String name;
    private String code;

    public Department(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Getters and setters

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (!name.equals(that.name)) return false;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }
}

```





```java
import java.util.Date;

public class Employee {
    private String name;
    private Date dateOfBirth;
    private Department department;
    private String socialSecurityNumber;
    private Address homeAddress;

    public static final String COMPANY_NAME = "TechCorp";

    public Employee(String name, Date dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialSecurityNumber = ssn;
        this.homeAddress = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", department=" + department +
                ", socialSecurityNumber='XXX-XX-XXXX'" +
                ", homeAddress=" + homeAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (!name.equals(employee.name)) return false;
        if (!dateOfBirth.equals(employee.dateOfBirth)) return false;
        if (!department.equals(employee.department)) return false;
        if (!socialSecurityNumber.equals(employee.socialSecurityNumber)) return false;
        return homeAddress.equals(employee.homeAddress);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + socialSecurityNumber.hashCode();
        result = 31 * result + homeAddress.hashCode();
        return result;
    }

    // Static utility method
    public static void printCompanyInfo() {
        System.out.println("All employees work at: " + COMPANY_NAME);
    }
}
```



```java
// Main.java
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        Department engineering = new Department("Engineering", "ENG");
        Address addr1 = new Address("123 Tech St", "San Francisco", "CA", "94107");
        Employee emp1 = new Employee("Alice", new Date(1990, 5, 15), engineering, "123-45-6789", addr1);

        Address addr2 = new Address("456 Dev Ave", "Mountain View", "CA", "94043");
        Employee emp2 = new Employee("Bob", new Date(1985, 8, 22), engineering, "987-65-4321", addr2);

        // Using reflection to inspect memory addresses
        Field field = Class.forName("java.lang.Object").getDeclaredField("address");
        field.setAccessible(true);

        long emp1Addr = field.getLong(emp1);
        long emp2Addr = field.getLong(emp2);

        System.out.println("emp1 memory address: " + Long.toHexString(emp1Addr));
        System.out.println("emp2 memory address: " + Long.toHexString(emp2Addr));
    }
}

```



Override toString method, so that when an Employee object is being printed, the print out looks meaningful and readble.

Override equals method, so that only when two Employees have identical information, we consider they are the same employee.

2.Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. You may use java relection utilities to demonstrate it.

3. Write static utilities in your Employee class, demostrate how static content differs from others during class instantiation.

```java
// In Employee.java
public static void printCompanyInfo() {
    System.out.println("All employees work at: " + COMPANY_NAME);
}

// Usage
Employee.printCompanyInfo(); // No instance needed
```



4. Explain why global variables are NOT recommended, you may use code snippets.

```java
public class Globals {
    public static int counter; // Global variable
}

public class App {
    public void incrementCounter() {
        Globals.counter++;
    }
}

```



5. Explain why Strings in Java are considered "Immutable"?

```java
String str = "Hello";
str.concat(" World"); // Does not modify original string
System.out.println(str); // Still prints "Hello"

```



6. Write code snippets to explain what does "Final" keyword do, and what we need it?

7. ```java
   final class FinalClass { /* Cannot be subclassed */ }
   
   public class Example {
       final int MAX_VALUE = 100; // Constant
       final String NAME;
   
       {
           NAME = "Immutable"; // Allowed in initializer block
       }
   
       public void method(final int param) {
           // param = 10; // Compilation error
       }
   }
   ```

   

7. Write code snippets to explain why Java is "pass-by-value", and why do some people think it might be "pass-by-reference"?

```java
final class FinalClass { /* Cannot be subclassed */ }

public class Example {
    final int MAX_VALUE = 100; // Constant
    final String NAME;

    {
        NAME = "Immutable"; // Allowed in initializer block
    }

    public void method(final int param) {
        // param = 10; // Compilation error
    }
}
```



8. Write code snippets to explain overloading in Java, explain how does Java define method signature.

```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(2, 3));       // Calls first
        System.out.println(calc.add(2.5, 3.1));   // Calls second
        System.out.println(calc.add(1, 2, 3));    // Calls third
    }
}


```



9. You may be asked to demo and explain your answers/code in class.

10. Use Java collection framework datastructures (e.g. Set, Map, List) to solve following Leetcode questions, you MUST use Java and you MUST use datastructures provided by Java Collection framwork:

1. Top K Frequent Elements（LeetCode 347）

```java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count how many times each number appears
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum); // Add 1 to the count for x
        }
        int maxCnt = Collections.max(cnt.values()); // Find the highest count

        // Step 2: Create buckets where index = frequency
        List<Integer>[] buckets = new ArrayList[maxCnt + 1];
        Arrays.setAll(buckets, i -> new ArrayList<>());
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            buckets[e.getValue()].add(e.getKey()); // Put number in the correct bucket
        }

        // Step 3: Collect the top k frequent numbers from buckets (from high to low)
        int[] ans = new int[k];
        int j = 0;
        for (int i = maxCnt; i >= 0 && j < k; i--) {
            for (int x : buckets[i]) {
                ans[j++] = x; // Add number to result
            }
        }
        return ans; // Return the answer
    }
}

```



1. Two Sum（LeetCode 1）

   ```java
   class Solution {
       public int[] twoSum(int[] nums, int target) {
           HashMap<Integer, Integer> valToIndex = new HashMap<>();
           for(int i = 0; i < nums.length; i++) {
               // need = target - nums[i]
               int need = target - nums[i];
               if(valToIndex.containsKey(need)) {
                   //if found, return 
                   return new int[]{valToIndex.get(need),i};
               }
               // add to map
               valToIndex.put(nums[i],i);
           }
           return null;
       }
   }
   ```

   

This is a separate coding task other than daily leetcoding questions, please submit your solutions as part of this assignment, rather than in the leetcoding sheet.

11. You can either have your code snippets in your markdown file, or include in a Java project.