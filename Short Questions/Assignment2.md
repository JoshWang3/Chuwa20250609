
## Question 1 & 3
```java
import java.time.LocalDate;
import java.util.Objects;

class Department {
    private String name;
    private String code;
    
    public Department(String name, String code) {
        this.name = name;
        this.code = code;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    @Override
    public String toString() {
        return "Department{name='" + name + "', code='" + code + "'}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department dept = (Department) obj;
        return Objects.equals(name, dept.name) && Objects.equals(code, dept.code);
    }
}

class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    
    public Address(String street, String city, String state, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    
    @Override
    public String toString() {
        return "Address{street='" + street + "', city='" + city + 
               "', state='" + state + "', zipCode='" + zipCode + "'}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return Objects.equals(street, address.street) &&
               Objects.equals(city, address.city) &&
               Objects.equals(state, address.state) &&
               Objects.equals(zipCode, address.zipCode);
    }
}

public class Employee {
    private String name;
    private LocalDate dateOfBirth;
    private Department department;
    private String socialSecurityNumber;
    private Address homeAddress;
    
    // property of class, not instance
    private static int totalEmployees = 0;
    
    public Employee(String name, LocalDate dateOfBirth, Department department, 
                   String socialSecurityNumber, Address homeAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.department = department;
        this.socialSecurityNumber = socialSecurityNumber;
        this.homeAddress = homeAddress;
        totalEmployees++;
    }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public String getSocialSecurityNumber() { return socialSecurityNumber; }
    public void setSocialSecurityNumber(String socialSecurityNumber) { 
        this.socialSecurityNumber = socialSecurityNumber; 
    }
    public Address getHomeAddress() { return homeAddress; }
    public void setHomeAddress(Address homeAddress) { this.homeAddress = homeAddress; }
    
    public static int getTotalEmployees() {
        return totalEmployees;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
               "name='" + name + '\'' +
               ", dateOfBirth=" + dateOfBirth +
               ", department=" + department +
               ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
               ", homeAddress=" + homeAddress +
               '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(name, employee.name) &&
               Objects.equals(dateOfBirth, employee.dateOfBirth) &&
               Objects.equals(department, employee.department) &&
               Objects.equals(socialSecurityNumber, employee.socialSecurityNumber) &&
               Objects.equals(homeAddress, employee.homeAddress);
    }
}

```

## Question 2

```java
import java.lang.reflect.Field;

public class MemoryDemo {
    public static void main(String[] args) throws Exception {
        Department dept = new Department("IT", "001");
        Address addr = new Address("1", "NY", "NY", "11111");
        
        Employee emp1 = new Employee("A B", LocalDate.of(2000, 1, 1), 
                                   dept, "123-45-6789", addr);
        Employee emp2 = new Employee("C D", LocalDate.of(2000, 1, 1), 
                                   dept, "987-65-4321", addr);
        // Different hash based on memory address, since different object
        System.out.println("emp1 identity hash: " + System.identityHashCode(emp1));
        System.out.println("emp2 identity hash: " + System.identityHashCode(emp2));
        
        Class<?> empClass = emp1.getClass();
        
        Field deptField = empClass.getDeclaredField("department");
        deptField.setAccessible(true);
        
        Department emp1Dept = (Department) deptField.get(emp1);
        Department emp2Dept = (Department) deptField.get(emp2);
        
        // Same hash based on memory address
        System.out.println("emp1 dept hash: " + System.identityHashCode(emp1Dept));
        System.out.println("emp2 dept hash: " + System.identityHashCode(emp2Dept));
        System.out.println("Same dept reference: " + (emp1Dept == emp2Dept));
    }
}
```



## Question 4
```java
public class BankAccount {
    // violation of encapsulation
    public static double balance = 1000.0; 
}

public class UnauthorizedClass {
    public void hacking() {
        // critical data modified without access control
        BankAccount.balance = 999999.0;
    }
}
```
```java
public class Utils {
    public static boolean flag;
}

public class OrderService {
    public void processOrder() {
	    Utils.flag = true;
    }
}

public class ReportService {
    public void generateReport() {
        Utils.flag = false;  // Unexpectedly overwrites value
    }
}
```

## Question 5
In its internal structure, the value array is private final, thus cannot be accessed or reassigned.

## Question 6

```java
public class FinalDemo {
   private final int MAX_SIZE = 100;        // Cannot be reassigned
   
   public void demonstrateFinal() {
       // MAX_SIZE = 200;  // error: cannot reassign final variable
   }
}

final class UtilityClass {              // Cannot be subclassed
   public final void criticalMethod() { 
       // Important logic, cannot be overridden
   }
}
```

## Question 7
```java
public class PassByValueDemo {
   
   public static void modify(int number) {
       number = 100; 
       System.out.println("Inside: " + number);  // 100
   }
   
   public static void main(String[] args) {
       int num = 50;
       modify(num);
       System.out.println("After: " + num);  // 50
   }
}
```
Why people think Java is "pass-by-reference":
When modify an object inside a method, the changes are visible after the method. 

## Question 8
Method signature = Method name + Parameter types
e.g. print(String); print(int); print(String, int); print(int, String)

## Question 10
```java
import java.util.*;

class Solution {
    
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> frequency.get(b) - frequency.get(a)
        );
        maxHeap.addAll(frequency.keySet());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }
}
```
```java
class Solution {
	public int[] twoSum(int[] nums, int  target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) return  new int[]{map.get(complement), i};
			map.put(nums[i], i);
		}

		return new int[]{};
	}
}
```

