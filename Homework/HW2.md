# HW2

Author: Yujiao Huang
Date: June 14, 2025

---

## 1. Write a Java POJO (plain old java object) named "Employee", 

inside Employ class, you should have

1. Employee's name

2. Employee's Date of Birth

3. Employee's Department (it can be another POJO)

4. Employee's Social Security Number

5. Employee's home address (it can be another POJO)

Override toString method, so that when an Employee object is being printed, the print out looks 

meaningful and readble.

Override equals method, so that only when two Employees have identical information, we consider they 

are the same employee.

### 1.1 Department POJO

```java
public class Department {
    private String name;
    private String code;

    public Department(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() { return name; }
    public String getCode() { return code; }
    public void setName(String name) { this.name = name; }
    public void setCode(String code) { this.code = code; }

    @Override
    public String toString() {
        return "Department{name='" + name + "', code='" + code + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Department)) return false;
        Department dept = (Department) obj;
        return name.equals(dept.name) && code.equals(dept.code);
    }
}
```

---

### 1.2 Address POJO

```java
public class Address {
    private String street;
    private String city;
    private String zipcode;

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZipcode() { return zipcode; }
    public void setStreet(String street) { this.street = street; }
    public void setCity(String city) { this.city = city; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + "', zipcode='" + zipcode + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        Address address = (Address) obj;
        return street.equals(address.street) &&
               city.equals(address.city) &&
               zipcode.equals(address.zipcode);
    }
}
```

---

### 1.3 Employee POJO

```java
import java.util.Date;

public class Employee {
    private String name;
    private Date dateOfBirth;
    private Department department;
    private String ssn;
    private Address address;

    // Static field
    public static int employeeCounter = 0;

    public Employee(String name, Date dateOfBirth, Department department, String ssn, Address address) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.ssn = ssn;
        this.address = address;
        employeeCounter++;
    }

    public String getName() { return name; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public Department getDepartment() { return department; }
    public String getSsn() { return ssn; }
    public Address getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setDepartment(Department department) { this.department = department; }
    public void setSsn(String ssn) { this.ssn = ssn; }
    public void setAddress(Address address) { this.address = address; }

    @Override
    public String toString() {
        return "Employee{" +
               "name='" + name + '\'' +
               ", dateOfBirth=" + dateOfBirth +
               ", department=" + department +
               ", ssn='" + ssn + '\'' +
               ", address=" + address +
               '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee emp = (Employee) obj;
        return name.equals(emp.name) &&
               dateOfBirth.equals(emp.dateOfBirth) &&
               department.equals(emp.department) &&
               ssn.equals(emp.ssn) &&
               address.equals(emp.address);
    }

    public static void printTotalEmployees() {
        System.out.println("Total Employees created: " + employeeCounter);
    }
}
```

---



## 2.   Write code to instantiate at least two instances of above Employee class, use code snippets to explain how these Employee objects are allocated to JVM memory. You may use java relection utilities to demonstrate it.

```java
import java.lang.reflect.Field;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Department dept1 = new Department("Engineering", "ENG01");
        Address addr1 = new Address("123 Main St", "Seattle", "98001");
        Employee emp1 = new Employee("Daisy", new Date(2001, 5, 15), dept1, "123-45-6789", addr1);

        Department dept2 = new Department("HR", "HR01");
        Address addr2 = new Address("456 Pine St", "Redmond", "98052");
        Employee emp2 = new Employee("Bob", new Date(2000, 7, 10), dept2, "987-65-4321", addr2);

        System.out.println(emp1);
        System.out.println(emp2);

        inspectObject(emp1);
        inspectObject(emp2);
    }

    public static void inspectObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting object of class: " + clazz.getName());
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName() + " = " + field.get(obj));
        }
        System.out.println("---------------");
    }
}
```

![](../screenshots/HW2_1.jpg)**Memory Explanation:**

- Objects are allocated in JVM **heap memory**.
-  Local variables ( `emp1` and `emp2` )that reference those objects live in **stack**. The variable emp1 itself is a **reference (a pointer)**, and this reference is stored in the **stack memory** because it’s a local variable inside main().
- And we also use  java relection utilities as above to demonstrate it. (Reflection can access and print object fields but not direct memory address.)

---



## 3. Write static utilities in your Employee class, demostrate how static content differs from others during class instantiation.

```java
// Employee class has included the static variable "employeeCounter"
Employee.printTotalEmployees();
```

- Static members belong to the class, not instances.
- All instances share the same static field. That's why the output shows that "employeeCounter = 2"

---



## 4. Why Global Variables Are NOT Recommended

```java
public class GlobalExample {
    public static int globalCounter = 0;

    public static void doSomething() {
        globalCounter++;
    }
}
```

**Disadvantages:**

- Thread unsafe(It may cause problems under multiple thread scenario)
- Tight coupling between modules
- Difficult unit testing and maintenance

---



## 5. Why Strings Are Immutable

```java
public class StringImmutability {
    public static void main(String[] args) {
        String a = "hello";
        String b = a;

        a = "world";

        System.out.println(b);  // Output: hello
    }
}
```

**Reasons:**

- **==String Pool==[answer keyword]:** Java stores string literals in a pool to save memory. Immutability ensures one reference does not change the value for others pointing to the same string.
- **Security**: Strings are used for sensitive data like usernames and passwords. Immutability prevents attackers from altering the values.
- **Thread Safety:** Since string values cannot be changed, they are automatically thread-safe, means multiple threads can safely use the same string.
- **Efficiency**: The JVM reuses strings in the String Pool  improving memory usage and performance.

---



## 6. Final Keyword Demonstration

```java
public class FinalExample {
    public final int id = 100;

    public final void show() {
        System.out.println("Cannot override me");
    }

    public static void main(String[] args) {
        final int x = 10;
        // x = 20; // Compile Error
    }
}
```

**Usage:**

- Prevent reassignment.
- Prevent method override.
- Prevent class inheritance.

---



## 7. Why Java is Pass-by-Value

```java
public class PassByValue {
    public static void changeValue(int x) {
        x = 10;
    }

    public static void changeObject(Address addr) {
        addr.setCity("Remond");
    }

    public static void main(String[] args) {
        int num = 5;
        changeValue(num);
        System.out.println(num);  // Output: 5

        Address addr = new Address("Street", "City", "123");
        changeObject(addr);
        System.out.println(addr.getCity());  // Output: Redmond
    }
}
```

**Explanation:**

- ==Java always passes values== (either primitive values or object references).
- For objects, the reference itself is passed by value, but the object contents can be modified.

---



## 8. Overloading and Method Signature

```java
public class OverloadingExample {
    public void print(String s) { System.out.println(s); }
    public void print(int i) { System.out.println(i); }
    public void print(String s, int i) { System.out.println(s + i); }
}
```

**Java method signature = method name + parameter list (types, order, count).**

---



## 9. LeetCode Problems Using Java Collections

### 9.1 LeetCode 347: Top K Frequent Elements

```java
import java.util.*;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(countMap::get));

        for (int num : countMap.keySet()) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = heap.poll();
        }
        return res;
    }
}
```

---

### 9.2 LeetCode 1: Two Sum

```java
import java.util.*;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No solution found");
    }
}
```



